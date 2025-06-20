package tyut.selab.framework.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tyut.selab.common.validator.Xss;

/**
 * @ClassName: UpdateUserDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2025-06-17 14:07
 * @Version: 1.0
 **/
@Data
@Schema(description = "修改用户信息DTO")
public class UpdateUserDto {
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @NotNull(message = "用户id不能为空")
    private Integer userId;
    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private Integer userAvatar;
    /**
     * 电话
     */
    @Schema(description = "电话")
    @Size(min=11, max=11,message="电话长度必须在为11位")
    private String userPhone;

//    @Schema(description = "用户密码")
//    @NotBlank(message = "用户密码不能为空")
//    @Xss(message = "用户密码不能包含脚本字符")
//    @Size(min=6, max=15,message="密码长度必须在 6 ~ 15 字符之间")
//    private String userPassword;

}
