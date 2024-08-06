package tyut.selab.modular.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tyut.selab.modular.domain.entity.HistoryEntity;

import java.util.Date;

@Data
@Schema(description = "组织历史Vo")
public class HistoryVo {

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @NotNull(message = "所处时期不能为空")
    @Schema(description = "所处时期")
    private Date historyTime;

    @NotNull(message = "标题不能为空")
    @Schema(description = "组织历史标题")
    @Size(min = 1,max = 30,message = "标题最大为30")
    private String historyTitle;
    /**
     * 标题图片
     */
    @Schema(description = "标题图片")
    private ImageVo headerImage;

    @NotNull(message = "具体事件不能为空")
    @Schema(description = "具体事件")
    @Size(min = 1,max = 200,message = "内容最大200")
    private String historyContent;

    public HistoryVo(HistoryEntity historyEntity){
        this.historyTime = historyEntity.getHistoryTime();
        this.historyContent = historyEntity.getHistoryContent();
        this.historyTitle = historyEntity.getHistoryTitle();

    }
}
