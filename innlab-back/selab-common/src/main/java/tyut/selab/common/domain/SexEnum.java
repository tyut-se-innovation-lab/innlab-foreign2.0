package tyut.selab.common.domain;

import lombok.Getter;

/**
 * @ClassName: SexEnum
 * @Description: 性别枚举
 * @Author: gmslymhn
 * @CreateTime: 2024-03-27 13:18
 * @Version: 1.0
 **/
public enum SexEnum {
    Unknown("0", "未知"),

    Male("1", "男"),

    Female("2", "女");

    @Getter
    private final String sexId ;

    @Getter
    private final String sexName;

    SexEnum(String sexId, String sexName) {
        this.sexId = sexId;
        this.sexName = sexName;
    }

}
