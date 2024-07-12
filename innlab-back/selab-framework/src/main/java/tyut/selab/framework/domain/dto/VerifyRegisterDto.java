package tyut.selab.framework.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tyut.selab.common.validator.Xss;

/**
 * @ClassName: VerifyRegisterDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-21 02:09
 * @Version: 1.0
 **/
@Data
@Schema(description = "用户注册验证")
public class VerifyRegisterDto {
    /**
     * 邀请码
     */
    @Schema(description = "邀请码")
    @Xss(message = "邀请码不能包含脚本字符")
    @NotBlank(message = "邀请码不能为空")
    @Size(min = 4, max = 10, message = "邀请码格式错误")
    private String invitationCode;
    @Schema(description = "用户注册账号")
    @Xss(message = "用户账号不能包含脚本字符")
    @NotBlank(message = "账号不能为空")
    @Size(min = 5, max = 20, message = "账号必须在5~20字符之间")
    private String userAccount;
    @Schema(description = "用户注册邮箱")
    @Email(message = "邮箱格式异常")
    @NotBlank(message = "邮箱不能为空")
    private String userEmail;
}
