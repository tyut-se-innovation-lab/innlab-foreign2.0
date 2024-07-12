package tyut.selab.framework.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tyut.selab.common.constant.UserConstants;

/**
 * @ClassName: UserMsgEntity
 * @Description: 用户信息
 * @Author: gmslymhn
 * @CreateTime: 2024-05-18 16:03
 * @Version: 1.0
 **/
@TableName("tyut_user_msg")
@Schema(description = "用户信息")
@Data
public class UserMsgEntity {
    /**
     * 用户id
     */
    @TableId("user_id")
    private Integer userId;
    /**
     * 用户姓名
     */
    @NotBlank(message = "姓名不能为空")
    @Size(min = UserConstants.USERNAME_MIN_LENGTH, max = UserConstants.USERNAME_MAX_LENGTH, message = "姓名必须在2~20字符之间")
    @TableField("user_name")
    private String userName;
    /**
     * 学号
     */
    @NotBlank(message = "学号不能为空")
    @Size(min = 10, max = 10, message = "太理的学号可是10位数呦")
    @TableField("user_number")
    private String userNumber;
    /**
     * 用户性别
     */
    @TableField("user_sex")
    private String userSex;
    /**
     * 用户头像
     */
    @TableField(" user_avatar")
    private Integer userAvatar;
    /**
     * 电话
     */
    @TableField("user_phone")
    @NotBlank(message = "电话不能为空")
    @Size(min=11, max=11,message="电话长度必须在为11位")
    private String userPhone;
    /**
     * 邮箱
     */
    @TableField("user_email")
    @Email
    private String userEmail;
    /**
     * 备注
     */
    @TableField("remark")
    @Size(max = 200, message = "备注最多200字")
    private String remark;

    /**
     * 逻辑删除(1删除 0未删除)
     */
    @TableLogic
    @TableField("del_flag")
    private Integer delFlag;
}
