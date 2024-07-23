package tyut.selab.framework.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import tyut.selab.common.constant.UserConstants;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @ClassName: UserEntity
 * @Description: 用户实体类
 * @Author: gmslymhn
 * @CreateTime: 2024-05-18 15:14
 * @Version: 1.0
 **/
@TableName("tyut_user")
@Data
@Schema(description = "用户实体类")
@NoArgsConstructor
public class UserEntity {
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;
    /**
     * 账号
     */
    @TableField("user_account")
    @Schema(description = "账号")
    @NotBlank(message = "账号不能为空")
    @Size(min = UserConstants.USERACCOUNT_MIN_LENGTH, max = UserConstants.USERACCOUNT_MAX_LENGTH, message = "账号必须在5~20字符之间")
    private String userAccount;
    /**
     * 邮箱
     */
    @TableField("user_email")
    @Email
    private String userEmail;
    /**
     * 密码
     */
    @JsonIgnore
    @TableField("user_password")
    @Schema(description = "密码")
    @NotBlank(message = "用户密码不能为空")
    @Size(min=UserConstants.PASSWORD_MIN_LENGTH, max=UserConstants.PASSWORD_MAX_LENGTH,message="密码长度必须在 6 ~ 15 字符之间")
    private String userPassword;
    /**
     * 用户姓名
     */
    @NotBlank(message = "昵称不能为空")
    @Schema(description = "用户昵称")
    @Size(min = UserConstants.USERNAME_MIN_LENGTH, max = UserConstants.USERNAME_MAX_LENGTH, message = "姓名必须在2~20字符之间")
    @TableField("user_nickname")
    private String userNickname;

    /**
     * 用户部门
     */
    @Schema(description = "用户部门")
    @TableField("user_department")
    @JsonIgnore
    private Integer userDepartment;
    /**
     * 用户部门
     */
    @Schema(description = "用户部门")
    @TableField(exist = false)
    private String userDepartmentName;
    /**
     * 角色id
     */
    @TableField("role_id")
    @Schema(description = "角色id")
    private Integer roleId;
    /**
     * 用户角色
     */
    @TableField(exist = false)
    private Set<String> userRole;
    /**
     * 登陆次数
     */
    @TableField("login_total")
    private Integer loginTotal;
    /**
     * 最后登陆ip
     */
    @TableField("last_login_ip")
    private String lastLoginIp;
    /**
     * 最后登陆地址
     */
    @TableField("last_login_location")
    private String lastLoginLocation;
    /**
     * 用户最后一次登陆时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;
    /**
     * 用户登陆状态
     */
    @TableField("user_state")
    private Boolean userState;
    /**
     * 逻辑删除(1删除 0未删除)
     */
    @TableLogic
    @TableField("del_flag")
    private Integer delFlag;

}
