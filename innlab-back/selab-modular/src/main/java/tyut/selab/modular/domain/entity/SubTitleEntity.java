package tyut.selab.modular.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName: SubTitleEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-11 12:52
 * @Version: 1.0
 **/
@TableName("tyut_subtitle")
@Schema(description = "日常活动小标题")
@AllArgsConstructor
@Data
public class SubTitleEntity {
    /**
     * 小标题id
     */
        @TableId(value = "subtitle_id",type = IdType.AUTO)
    private Integer subtitleId;
    /**
     * 活动id
     */
    @TableField("activity_id")
    private Integer activityId;
    /**
     * 小标题名
     */
    @TableField("subtitle_name")
    private String subtitleName;
    /**
     * 小标题类型
     */
    @TableField("subtitle_type")
    private Integer subtitleType;
    /**
     * 小标题内容
     */
    @TableField("subtitle_content")
    private String subtitleContent;;
    /**
     * 小标题权重
     */
    @TableField("subtitle_sort")
    private String subtitleSort;;
}
