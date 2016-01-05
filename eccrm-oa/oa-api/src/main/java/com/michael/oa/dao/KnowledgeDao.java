package com.michael.oa.dao;

import com.michael.oa.bo.KnowledgeBo;
import com.michael.oa.domain.Knowledge;
import com.michael.oa.vo.KnowledgeVo;
import java.util.List;

/**
 * @author Michael
 */
public interface KnowledgeDao {

    String save(Knowledge knowledge);

    void update(Knowledge knowledge);

    /**
     * 高级查询接口
     */
    List<Knowledge> query(KnowledgeBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(KnowledgeBo bo);

    Knowledge findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(Knowledge knowledge);
}
