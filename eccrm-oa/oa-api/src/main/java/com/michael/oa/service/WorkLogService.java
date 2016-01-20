package com.michael.oa.service;

import com.michael.oa.bo.WorkLogBo;
import com.michael.oa.domain.WorkLog;
import com.michael.oa.vo.WorkLogVo;
import com.ycrl.core.pager.PageVo;

/**
 * @author Michael
 */
public interface WorkLogService {

    /**
     * 保存
     */
    String save(WorkLog workLog);

    /**
     * 更新
     */
    void update(WorkLog workLog);

    /**
     * 分页查询
     */
    PageVo pageQuery(WorkLogBo bo);

    /**
     * 根据ID查询对象的信息
     */
    WorkLogVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

}
