package tyut.selab.modular.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: UpdateHistoryDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-17 15:28
 * @Version: 1.0
 **/
@Data
@Schema(description = "修改项目DTO")
public class UpdateHistoryDto {
    /**
     * 组织历史ID
     */
    @Schema(description = "组织历史id")
    @NotNull(message = "组织历史id不能为null")
    private Integer historyId;
    /**
     * 所处时期
     */
    @Schema(description = "所处时期")
    private Date historyTime;
    /**
     * 历史标题
     */
    @Schema(description = "历史标题")
    @Size(min = 1,max = 30,message = "历史标题必须在1-30字符之间！")
    private String historyTitle;


    @Schema(description = "标题图片id")
    private Integer headerImageId;

    /**
     * 具体事件
     */
    @Schema(description = "具体事件")
    @Size(min = 1,max = 200,message = "具体内容必须在1-200字符之间！")
    private String historyContent;
    /**
     * 历史状态（1保留/0删除）
     */
    private Boolean state;
}
