package tyut.selab.framework.domain.dto.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: LogParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-23 10:28
 * @Version: 1.0
 **/
@Data
@Schema(description = "日志分页信息")
@AllArgsConstructor
@NoArgsConstructor
public class LogParam extends PageParam{
    @Size(min = 5, max = 20, message = "账号必须在5~20字符之间")
    private String userAccount;
    @Size(max = 16, message = "ip最长16字符")
    private String ip;
}
