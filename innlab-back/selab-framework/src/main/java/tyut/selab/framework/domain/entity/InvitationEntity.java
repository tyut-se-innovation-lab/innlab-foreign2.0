package tyut.selab.framework.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName: InvitationEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-12 14:01
 * @Version: 1.0
 **/
@Data
@TableName("tyut_invitation")
public class InvitationEntity {
    /**
     * id
     */
    @TableId(value = "invitation_id",type = IdType.AUTO)
    private Integer invitationId;
    /**
     * 邀请码
     */
    @TableField("invitation_code")
    private String invitationCode;
    /**
     * 角色id
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 部门id
     */
    @TableField("department_id")
    private Integer departmentId;
    /**
     * 状态
     */
    @TableField("state")
    private Boolean state;
    /**
     * 逻辑删除
     */
    @TableField("del_flag")
    private Integer delFlag;
}
