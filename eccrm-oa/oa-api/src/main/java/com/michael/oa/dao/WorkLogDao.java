package com.michael.oa.dao;

import com.michael.oa.bo.WorkLogBo;
import com.michael.oa.domain.WorkLog;

import java.util.List;

/**
 * @author Michael
 */
public interface WorkLogDao {

    String save(WorkLog workLog);

    void update(WorkLog workLog);

    /**
     * 高级查询接口
     */
    List<WorkLog> query(WorkLogBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(WorkLogBo bo);

    WorkLog findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(WorkLog workLog);
}
