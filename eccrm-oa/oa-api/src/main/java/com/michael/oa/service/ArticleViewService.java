package com.michael.oa.service;

import com.michael.oa.bo.ArticleViewBo;
import com.michael.oa.domain.ArticleView;
import com.michael.oa.vo.ArticleViewVo;
import com.ycrl.core.pager.PageVo;

/**
 * @author Michael
 */
public interface ArticleViewService {

    /**
     * 保存
     */
    String save(ArticleView articleView);

    /**
     * 分页查询
     */
    PageVo pageQuery(ArticleViewBo bo);

    /**
     * 根据ID查询对象的信息
     */
    ArticleViewVo findById(String id);


}
