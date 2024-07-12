package tyut.selab.framework.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @ClassName: LoginLogEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-12 20:21
 * @Version: 1.0
 **/
@Data
@TableName("tyut_login_log")
public class LoginLogEntity {
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
     * 登陆ip
     */
    @TableField("login_ip")
    private String loginIp;
    /**
     * 操作系统
     */
    @TableField("os_name")
    private String osName;
    /**
     * 浏览器类型
     */
    @TableField("browser")
    private String browser;
    /**
     * 登陆地点
     */
    @TableField("login_location")
    private String loginLocation;
    /**
     * 登陆返回信息
     */
    @TableField("result_message")
    private String resultMessage;
    /**
     * 登陆返回Code
     */
    @TableField("result_code")
    private Integer resultCode;
    /**
     * 登陆时间
     */
    @TableField("login_time")
    private String loginTime;
}
