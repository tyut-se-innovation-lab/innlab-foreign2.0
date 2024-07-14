package tyut.selab.modular.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: UpdateHistoryDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-14 21:06
 * @Version: 1.0
 **/
@Data
@Schema(description = "修改组织历史")
public class HistoryDto {
    public interface Add {
    }
    public interface Update {
    }
    /**
     * 组织历史ID
     */
    @Schema(description = "组织历史id")
    @NotNull(message = "组织历史id不能为null",groups = Update.class)
    private Integer historyId;
    /**
     * 所处时期
     */
    @Schema(description = "所处时期")
    @NotNull(message = "所处时期不能为null",groups = Add.class)
    private Date historyTime;
    /**
     * 历史标题
     */
    @Schema(description = "历史标题")
    @NotNull(message = "历史标题不能为null",groups = Add.class)
    @Size(min = 1,max = 20,message = "历史标题必须在1-20字符之间！",groups = {Add.class,Update.class})
    private String historyTitle;

    /**
     * 具体事件
     */
    @Schema(description = "具体事件")
    @NotNull(message = "具体事件不能为null",groups = Add.class)
    @Size(min = 1,max = 200,message = "具体内容必须在1-200字符之间！",groups = {Add.class,Update.class})
    private String historyContent;
    /**
     * 历史状态（1保留/0删除）
     */
    private Boolean state;
}
