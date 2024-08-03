package tyut.selab.modular.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tyut.selab.common.domain.BaseEntity;
import java.util.Date;

@Data
@TableName("tyut_history")
@Schema(description = "组织历史实体类")
@EqualsAndHashCode(callSuper=true)
public class HistoryEntity extends BaseEntity {
    /**
     * 组织历史ID
     */
    @TableId(value = "history_id",type = IdType.AUTO)
    private Integer historyId;
    /**
     * 所处时期
     */
    @TableField(value = "history_time")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date historyTime;

    /**
     * 历史标题
     */
    @Size(min = 1,max = 20,message = "历史标题必须在1-20字符之间！")
    @TableField(value = "history_title")
    private String historyTitle;

    /**
     * 具体事件
     */
    @TableField(value = "history_content")
    @Size(min = 1,max = 200,message = "具体内容必须在1-200字符之间！")
    private String historyContent;
    /**
     * 历史状态（1保留/0删除）
     */
    @TableField(value = "state")
    private Boolean state;

}
