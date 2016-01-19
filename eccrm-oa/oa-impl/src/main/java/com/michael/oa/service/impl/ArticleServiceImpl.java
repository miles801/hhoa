package com.michael.oa.service.impl;

import com.michael.oa.bo.ArticleBo;
import com.michael.oa.dao.ArticleDao;
import com.michael.oa.dao.ArticleViewDao;
import com.michael.oa.dao.ModuleDao;
import com.michael.oa.domain.Article;
import com.michael.oa.domain.ArticleView;
import com.michael.oa.domain.Module;
import com.michael.oa.service.ArticleService;
import com.michael.oa.vo.ArticleVo;
import com.ycrl.base.common.CommonStatus;
import com.ycrl.base.common.CommonSysParam;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.beans.BeanWrapCallback;
import com.ycrl.core.context.SecurityContext;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import com.ycrl.core.pager.PageVo;
import eccrm.base.parameter.service.ParameterContainer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Michael
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService, BeanWrapCallback<Article, ArticleVo> {
    @Resource
    private ArticleDao articleDao;

    @Resource
    private ArticleViewDao articleViewDao;

    @Resource
    private ModuleDao moduleDao;

    @Override
    public String save(Article article) {
        ValidatorUtils.validate(article);
        // 保存临时状态
        String status = article.getStatus();
        if (CommonStatus.ACTIVE.getValue().equals(status)) {
            article.setStatus(CommonStatus.INACTIVE.getValue());
        }
        String id = articleDao.save(article);
        // 发布文章
        if (status.equals(CommonStatus.ACTIVE.getValue())) {
            publish(new String[]{id});
        }
        return id;
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }

    @Override
    public PageVo pageQuery(ArticleBo bo) {
        PageVo vo = new PageVo();
        Long total = articleDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<Article> articleList = articleDao.query(bo);
        List<ArticleVo> vos = BeanWrapBuilder.newInstance()
                .setCallback(this)
                .wrapList(articleList, ArticleVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public ArticleVo findById(String id) {
        Article article = articleDao.findById(id);
        return BeanWrapBuilder.newInstance()
                .wrap(article, ArticleVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            Article article = articleDao.findById(id);
            if (article == null) {
                continue;
            }

            if (CommonStatus.ACTIVE.getValue().equals(article.getStatus())) {
                article.setStatus(CommonStatus.CANCELED.getValue());
            } else {
                articleDao.deleteById(id);
            }
        }
    }

    @Override
    public void publish(String[] ids) {
        Assert.notEmpty(ids, "帖子ID不能为空!");
        for (String id : ids) {
            Article article = articleDao.findById(id);
            if (article != null && !CommonStatus.ACTIVE.getValue().equals(article.getStatus())) {
                // 设置发布信息
                article.setPublishTime(new Date());
                article.setStatus(CommonStatus.ACTIVE.getValue());

                // 更新版块的帖子数量
                Module module = moduleDao.findById(article.getModuleId());
                synchronized (module) {
                    int counts = module.getArticleCounts() == null ? 0 : module.getArticleCounts();
                    module.setArticleCounts(counts + 1);
                    module.setArticleLastTime(new Date());
                }
            }
        }
    }

    @Override
    public synchronized Integer view(String articleId) {
        Assert.hasText(articleId, "阅读帖子:帖子ID不能为空!");
        Date date = articleViewDao.find(articleId, SecurityContext.getUserId());
        if (date == null) {
            // 保存阅读记录
            ArticleView articleView = new ArticleView();
            articleView.setArticleId(articleId);
            articleViewDao.save(articleView);

            // 更新阅读数量
            Article article = articleDao.findById(articleId);
            article.setLastViewTime(new Date());
            int viewCounts = article.getViewCounts() == null ? 0 : article.getViewCounts();
            article.setViewCounts(viewCounts + 1);
            return article.getViewCounts();
        }
        return null;
    }

    @Override
    public void doCallback(Article article, ArticleVo vo) {
        ParameterContainer container = ParameterContainer.getInstance();
        vo.setStatusName(container.getSystemName(CommonSysParam.STATUS, article.getStatus()));
    }
}
