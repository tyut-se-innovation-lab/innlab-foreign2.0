package tyut.selab.framework.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: RoleEntity
 * @Description:
 * @Author: gmslymhn-15 19:56
 * @CreateTime: 2024-07
 * @Version: 1.0
 **/
@TableName("tyut_role")
@Data
@NoArgsConstructor
public class RoleEntity {
    /**
     * 角色id
     */
    @TableId(value = "role_id" ,type = IdType.AUTO)
    private Integer roleId;
    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;
    /**
     * 角色key
     */
    @TableField("role_key")
    private String roleKey;
    /**
     * 描述
     */
    @TableField("description")
    private String description;
    /**
     * 状态
     */
    @TableField("state")
    private Boolean state;
    /**
     * 状态
     */
    @TableField("del_flag")
    private Integer delFlag;

}
