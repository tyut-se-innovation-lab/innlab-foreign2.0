package tyut.selab.framework.domain.dto.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: PageParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-22 23:42
 * @Version: 1.0
 **/
@Data
@Schema(description = "分页信息")
@AllArgsConstructor
@NoArgsConstructor
public class PageParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Integer PAGE_NUM = 1;
    private static final Integer PAGE_SIZE = 10;

    @Schema(description = "页码")
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNum = PAGE_NUM;

    @Schema(description = "每页条数")
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数最小值为 1")
    @Max(value = 100, message = "每页条数最大值为 100")
    private Integer pageSize = PAGE_SIZE;
}
