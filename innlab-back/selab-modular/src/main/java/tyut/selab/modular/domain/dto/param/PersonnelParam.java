package tyut.selab.modular.domain.dto.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tyut.selab.framework.domain.PageParam;

/**
 * @ClassName: historyParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-27 17:15
 * @Version: 1.0
 **/
@Data
@Schema(description = "组织历史Param")
public class PersonnelParam extends PageParam {

    /**
     * 部门
     */
    @Schema(description = "部门")
    @Size(min = 4,max = 4,message = "部门字符必须为4")
    private String department;

    /**
     * 部门
     */
    @Schema(description = "年代")
    @Min(value = 2020, message = "年代最低为2020年")
    @Max(value = 2025, message = "年代最后高为2025年")
    private Integer period;
}
