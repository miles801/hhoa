package com.michael.oa.service;

import com.michael.oa.bo.BlackListBo;
import com.michael.oa.domain.BlackList;
import com.michael.oa.vo.BlackListVo;
import com.ycrl.core.pager.PageVo;

/**
 * @author Michael
 */
public interface BlackListService {

    /**
     * 保存
     */
    String save(BlackList blackList);

    /**
     * 更新
     */
    void update(BlackList blackList);

    /**
     * 分页查询
     */
    PageVo pageQuery(BlackListBo bo);

    /**
     * 根据ID查询对象的信息
     */
    BlackListVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

}
