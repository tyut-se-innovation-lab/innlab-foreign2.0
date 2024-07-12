package tyut.selab.common.domain;

import lombok.Getter;

/**
 * @ClassName: DepartmentEnum
 * @Description: 部门枚举
 * @Author: gmslymhn
 * @CreateTime: 2024-03-27 12:57
 * @Version: 1.0
 **/
public enum DepartmentEnum {
    INNLAB("0", "创新实验室"),

    Software_Development("1", "软件开发"),

    Cybersecurity("2", "网络安全"),

    Artificial_Intelligence("3", "人工智能"),

    Virtual_Reality("4", "虚拟现实"),
    Algorithm_Competition("5", "算法竞赛");

    @Getter
    private final String departmentId ;

    @Getter
    private final String departmentName;

    DepartmentEnum(String departmentId, String department) {
        this.departmentId = departmentId;
        this.departmentName = department;
    }

}
