package tyut.selab.framework.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName: AccessLogEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-15 20:21
 * @Version: 1.0
 **/
@Data
@TableName("tyut_access_log")
public class AccessLogEntity {
    /**
     * id
     */
    @TableId(value = "log_id",type = IdType.AUTO)
    private Integer logId;
    /**
     * 访问ip
     */
    @TableField("access_ip")
    private String accessIp;
    /**
     * 访问Url
     */
    @TableField("access_url")
    private String accessUrl;
    /**
     * 访问Url
     */
    @TableField("method")
    private String method;
    /**
     * 访问Url
     */
    @TableField("access_time")
    private String accessTime;
}
