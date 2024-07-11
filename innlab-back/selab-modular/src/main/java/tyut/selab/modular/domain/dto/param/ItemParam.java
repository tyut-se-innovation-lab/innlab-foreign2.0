package tyut.selab.modular.domain.dto.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tyut.selab.framework.domain.PageParam;

/**
 * @ClassName: ItemParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-24 17:00
 * @Version: 1.0
 **/
@Data
@Schema(description = "项目Param")
public class ItemParam extends PageParam {
    /**
     * 部门
     */
    @Schema(description = "部门")
    @NotBlank(message = "部门不能为空")
    private String department;
}
