package tyut.selab.framework.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName: SysLogEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-12 15:24
 * @Version: 1.0
 **/
@Data
@TableName("tyut_sys_log")
public class SysLogEntity {
    /**
     * id
     */
    @TableId(value = "log_id",type = IdType.AUTO)
    private Integer logId;
    /**
     * 用户账号
     */
    @TableField("user_account")
    private String userAccount;
    /**
     * url
     */
    @TableField("url")
    private String url;
    /**
     * 操作参数
     */
    @TableField("method")
    private String method;
    /**
     * 操作参数
     */
    @TableField("params")
    private String params;
    /**
     * ip
     */
    @TableField("ip")
    private String ip;
    /**
     * 操作类型
     */
    @TableField("type")
    private String type;
    /**
     * 模块
     */
    @TableField("model")
    private String model;
    /**
     * 操作结果Code
     */
    @TableField("result_code")
    private Integer resultCode;
    /**
     * 操作结果
     */
    @TableField("result_message")
    private String resultMessage;
    /**
     * 描述
     */
    @TableField("description")
    private String description;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

}
