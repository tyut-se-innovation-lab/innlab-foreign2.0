package tyut.selab.framework.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName: MsgVo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-22 23:36
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@Schema(description = "个人信息")
public class UserMsgVo {
    private String account;
    private String nickname;
    private String avatarUrl;
    private String email;
    private String phone;
    private String department;
    /**
     * 最后登陆ip
     */
    private String lastLoginIp;
    private String lastLoginLocation;
    /**
     * 用户最后一次登陆时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;
    /**
     * 角色
     */
    private String role;

}
