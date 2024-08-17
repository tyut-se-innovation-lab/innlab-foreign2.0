package tyut.selab.modular.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: AddHistoryDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-17 15:28
 * @Version: 1.0
 **/
@Data
@Schema(description = "添加项目DTO")
public class AddHistoryDto {
    /**
     * 所处时期
     */
    @Schema(description = "所处时期")
    @NotNull(message = "所处时期不能为null")
    private Date historyTime;
    /**
     * 历史标题
     */
    @Schema(description = "历史标题")
    @NotNull(message = "历史标题不能为null")
    @Size(min = 1,max = 20,message = "历史标题必须在1-20字符之间！")
    private String historyTitle;

    @Schema(description = "标题图片id")
    @NotNull(message = "标题图片id不能为null")
    private Integer headerImageId;

    /**
     * 具体事件
     */
    @Schema(description = "具体事件")
    @NotNull(message = "具体事件不能为null")
    @Size(min = 1,max = 200,message = "具体内容必须在1-200字符之间！")
    private String historyContent;
}
