package tyut.selab.framework.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tyut.selab.common.validator.Xss;

/**
 * @ClassName: GmLoginDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 17:43
 * @Version: 1.0
 **/
@Data
@Schema(description = "登陆信息")
public class LoginDto {
    @Schema(description = "登陆账号")
    @Xss(message = "用户账号不能包含脚本字符")
    @NotBlank(message = "账号不能为空")
    @Size(min = 5, max = 20, message = "账号必须在5~20字符之间")
    private String account;

    @Schema(description = "登陆密码")
    @NotBlank(message = "用户密码不能为空")
    @Xss(message = "用户密码不能包含脚本字符")
    @Size(min=6, max=15,message="密码长度必须在 6 ~ 15 字符之间")
    private String password;
}
