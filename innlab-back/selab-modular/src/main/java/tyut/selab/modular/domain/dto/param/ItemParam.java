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
    @Size(min = 4,max = 4,message = "部门字符必须为4")
    private String department;
}
