package com.michael.oa.dao;

import com.michael.oa.bo.BlackListBo;
import com.michael.oa.domain.BlackList;

import java.util.List;

/**
 * @author Michael
 */
public interface BlackListDao {

    String save(BlackList blackList);

    void update(BlackList blackList);

    /**
     * 高级查询接口
     */
    List<BlackList> query(BlackListBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(BlackListBo bo);

    BlackList findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(BlackList blackList);
}
