package eccrm.base.employee.service;

import com.ycrl.core.beans.BeanWrapper;
import com.ycrl.core.pager.PageVo;
import eccrm.base.employee.bo.EmployeePositionBo;
import eccrm.base.employee.domain.EmployeePosition;
import eccrm.base.employee.vo.EmployeePositionVo;

import java.util.List;

/**
* Generated by yanhx on 2014-10-13.
*/

public interface EmployeePositionService extends BeanWrapper<EmployeePosition, EmployeePositionVo>{

    String save(EmployeePosition employeePosition);

    void update(EmployeePosition employeePosition);

    PageVo query(EmployeePositionBo bo);

    EmployeePositionVo findById(String id);

    void deleteByIds(String... ids);
    List<EmployeePosition> findByRuleId(String id);
}