package tyut.selab.modular.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private Date historyTime;

    /**
     * 历史标题
     */
    @TableField(value = "history_title")
    private String historyTitle;

    /**
     * 具体事件
     */
    @TableField(value = "history_content")
    private String historyContent;
    /**
     * 历史状态（1保留/0删除）
     */
    @TableField(value = "state")
    private Boolean state;

}
