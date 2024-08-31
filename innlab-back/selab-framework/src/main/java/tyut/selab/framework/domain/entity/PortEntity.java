package tyut.selab.framework.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PortEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-18 15:26
 * @Version: 1.0
 **/
@TableName("tyut_port")
@Data
@NoArgsConstructor
public class PortEntity {
    /**
     * 接口id
     */
    @TableId(value = "port_id" ,type = IdType.AUTO)
    private Integer portId;
    /**
     * 接口名称
     */
    @TableField("port_name")
    private String portName;
    /**
     * 接口key
     */
    @TableField("port_key")
    private String portKey;
    /**
     * 描述
     */
    @TableField("description")
    private String description;
    /**
     * 状态
     */
    @TableField("del_flag")
    private Integer delFlag;
}
