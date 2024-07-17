package tyut.selab.common.utils;

import tyut.selab.common.domain.DepartmentEnum;
import tyut.selab.common.domain.SexEnum;
import tyut.selab.common.exception.ErrorCode;
import tyut.selab.common.exception.ServiceException;

/**
 * @ClassName: EnumUtils
 * @Description: 枚举工具类
 * @Author: gmslymhn
 * @CreateTime: 2024-03-27 13:15
 * @Version: 1.0
 **/
public class EnumUtils {
    public static DepartmentEnum getDepartmentById(String departmentId) {
        for (DepartmentEnum department : DepartmentEnum.values()) {
            if (department.getDepartmentId().equals(departmentId)) {
                return department;
            }
        }
        return DepartmentEnum.INNLAB; // 如果未找到对应的部门ID，可以根据实际情况返回null或者抛出异常
    }

    public static Integer getDepartmentIdByName(String departmentName) {
        for (DepartmentEnum department : DepartmentEnum.values()) {
            if (department.getDepartmentName().equals(departmentName)) {
                return Integer.valueOf(department.getDepartmentId());
            }
        }
        throw new ServiceException("输入部门异常！", ErrorCode.ACCOUNT_ABNORMAL); // 如果未找到对应的部门名称，可以根据实际情况返回null或者抛出异常
    }
    public static String getDepartmentNameById(String departmentId) {
        for (DepartmentEnum department : DepartmentEnum.values()) {
            if (department.getDepartmentId().equals(departmentId)) {
                return department.getDepartmentName();
            }
        }
        throw new ServiceException("输入部门异常！", ErrorCode.ACCOUNT_ABNORMAL); // 如果未找到对应的部门名称，可以根据实际情况返回null或者抛出异常
    }
    public static String getSexNameById(String sexId) {
        for (SexEnum sex : SexEnum.values()) {
            if (sex.getSexId().equals(sexId)) {
                return sex.getSexName();
            }
        }
        return SexEnum.Unknown.getSexName(); // 如果未找到对应的部门名称，可以根据实际情况返回null或者抛出异常
    }
    public static String getSexIdByName(String sexName) {
        for (SexEnum sex : SexEnum.values()) {
            if (sex.getSexName().equals(sexName)) {
                return sex.getSexId();
            }
        }
        return SexEnum.Unknown.getSexId(); // 如果未找到对应的部门名称，可以根据实际情况返回null或者抛出异常
    }

    public static void main(String[] args) {
        DepartmentEnum departmentEnum = getDepartmentById("1");
        System.out.println(departmentEnum.getDepartmentName());
    }

}
