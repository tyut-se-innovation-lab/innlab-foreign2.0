package tyut.selab.modular.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ClassName: AddSubTitleDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-02 23:51
 * @Version: 1.0
 **/
@Data
@Schema(description = "添加小标题DTO")
public class AddSubTitleDto {
    /**
     * 活动id
     */
    @NotNull(message = "活动id不能为null")
    @Schema(description = "活动id")
    private Integer activityId;
    /**
     * 小标题类型
     */
    @Schema(description = "小标题类型")
    @NotNull(message = "小标题类型不能为null")
    @Max(value = 9,message = "小标题类型异常！")
    private Integer subtitleType;
    /**
     * 小标题权重
     */
    @NotNull(message = "小标题权重不能为null")
    @Max(value = 99,message = "小标题权重异常！")
    private Integer subtitleSort;
}
