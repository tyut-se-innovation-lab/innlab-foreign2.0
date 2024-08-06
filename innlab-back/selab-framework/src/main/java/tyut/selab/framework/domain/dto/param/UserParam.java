package tyut.selab.framework.domain.dto.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: UserParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-26 22:13
 * @Version: 1.0
 **/
@Data
@Schema(description = "用户分页信息")
public class UserParam extends PageParam {
    @Size(max = 20, message = "账号必须在5~20字符之间")
    private String userAccount;
    @Size(min = 4, max = 4, message = "用户部门必须为4字符")
    private String userDepartment;
}
