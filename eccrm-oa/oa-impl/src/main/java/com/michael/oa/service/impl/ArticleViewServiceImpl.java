package com.michael.oa.service.impl;

import com.michael.oa.bo.ArticleViewBo;
import com.michael.oa.dao.ArticleViewDao;
import com.michael.oa.domain.ArticleView;
import com.michael.oa.service.ArticleViewService;
import com.michael.oa.vo.ArticleViewVo;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.pager.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Michael
 */
@Service("articleViewService")
public class ArticleViewServiceImpl implements ArticleViewService {
    @Resource
    private ArticleViewDao articleViewDao;

    @Override
    public String save(ArticleView articleView) {
        String id = articleViewDao.save(articleView);
        return id;
    }


    @Override
    public PageVo pageQuery(ArticleViewBo bo) {
        PageVo vo = new PageVo();
        Long total = articleViewDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<ArticleView> articleViewList = articleViewDao.query(bo);
        List<ArticleViewVo> vos = BeanWrapBuilder.newInstance()
                .wrapList(articleViewList, ArticleViewVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public ArticleViewVo findById(String id) {
        ArticleView articleView = articleViewDao.findById(id);
        return BeanWrapBuilder.newInstance()
                .wrap(articleView, ArticleViewVo.class);
    }


}
