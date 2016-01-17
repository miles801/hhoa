package com.michael.oa.service.impl;

import com.michael.oa.bo.ArticleBo;
import com.michael.oa.dao.ArticleDao;
import com.michael.oa.domain.Article;
import com.michael.oa.service.ArticleService;
import com.michael.oa.vo.ArticleVo;
import com.ycrl.base.common.CommonStatus;
import com.ycrl.base.common.CommonSysParam;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.beans.BeanWrapCallback;
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

    @Override
    public String save(Article article) {
        String id = articleDao.save(article);
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
                article.setPublishTime(new Date());
                article.setStatus(CommonStatus.ACTIVE.getValue());
            }
        }
    }

    @Override
    public void doCallback(Article article, ArticleVo vo) {
        ParameterContainer container = ParameterContainer.getInstance();
        vo.setStatusName(container.getSystemName(CommonSysParam.STATUS, article.getStatus()));
    }
}
