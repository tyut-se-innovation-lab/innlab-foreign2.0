package tyut.selab.framework.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tyut.selab.common.validator.Xss;

/**
 * @ClassName: UpdateUserPasswordDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2025-06-17 14:47
 * @Version: 1.0
 **/
@Data
public class UpdateUserPasswordDto {

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @Schema(description = "旧用户密码")
    @NotBlank(message = "用户密码不能为空")
    @Xss(message = "用户密码不能包含脚本字符")
    @Size(min=6, max=15,message="密码长度必须在 6 ~ 15 字符之间")
    private String oldUserPassword;


    @Schema(description = "新用户密码")
    @NotBlank(message = "用户密码不能为空")
    @Xss(message = "用户密码不能包含脚本字符")
    @Size(min=6, max=15,message="密码长度必须在 6 ~ 15 字符之间")
    private String newUserPassword;
}
