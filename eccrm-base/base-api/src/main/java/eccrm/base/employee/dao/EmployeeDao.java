package eccrm.base.employee.dao;


import eccrm.base.employee.bo.EmployeeBo;
import eccrm.base.employee.domain.Employee;
import eccrm.base.log.OperateType;
import eccrm.base.log.annotations.LogInfo;

import java.util.List;

/**
 * Generated by yanhx on 2014-10-13.
 */

public interface EmployeeDao {

    @LogInfo(type = OperateType.ADD, describe = "员工-新增")
    String save(Employee employee);

    @LogInfo(type = OperateType.MODIFY, describe = "员工-修改")
    void update(Employee employee);

    List<Employee> query(EmployeeBo bo);

    List<Employee> querys(EmployeeBo bo, String ids);

    long getTotal(EmployeeBo bo);

    Employee findById(String id);

    Employee findByCode(String id);


    /**
     * 根据员工ID获取员工的名称
     *
     * @param employeeId 员工id
     */
    String findNameById(String employeeId);

    @LogInfo(type = OperateType.DELETE, describe = "员工-删除")
    int deleteById(String id);

    /**
     * 判断指定rtxId的员工是否存在
     *
     * @param rtxId
     * @return true存在，false不存在
     */
    boolean isExists(String rtxId);

    /**
     * 根据RTXid查询员工
     *
     * @param rtxId rtxId
     */
    Employee findByRtxId(String rtxId);
}