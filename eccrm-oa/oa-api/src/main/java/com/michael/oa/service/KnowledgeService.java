package com.michael.oa.service;

import com.ycrl.core.pager.PageVo;
import com.michael.oa.bo.KnowledgeBo;
import com.michael.oa.domain.Knowledge;
import com.michael.oa.vo.KnowledgeVo;

/**
 * @author Michael
 * 
 */
public interface KnowledgeService {
    /**
     * 业务参数：知识类型
     */
    String TYPE = "KN_TYPE";
    /**
     * 保存
     */
    String save(Knowledge knowledge);

    /**
     * 更新
     */
    void update(Knowledge knowledge);

    /**
     * 分页查询
     */
    PageVo pageQuery(KnowledgeBo bo);

    /**
     * 根据ID查询对象的信息
     */
    KnowledgeVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

    /**
     * 批量注销
     * @param ids 知识ID集合
     */
    void batchCancel(String[] ids);

    /**
     * 批量启用
     * @param ids 知识ID集合
     */
    void batchActive(String[] ids);



}
