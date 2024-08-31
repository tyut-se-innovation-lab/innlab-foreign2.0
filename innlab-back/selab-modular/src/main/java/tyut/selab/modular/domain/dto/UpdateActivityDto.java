package tyut.selab.modular.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: UpdateActivityDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-23 13:22
 * @Version: 1.0
 **/
@Data
@Schema(description = "修改活动DTO")
public class UpdateActivityDto {
    /**
     * 活动id
     */
    @TableId(value = "activity_id",type = IdType.AUTO)
    @Schema(description = "活动id")
    @NotNull(message = "活动id不能为null")
    private Integer activityId;

    /**
     * 活动标题
     */
    @Schema(description = "活动标题")
    @Size(min = 1,max = 30,message = "活动标题必须在1-30字符之间！")
    private String activityTitle;
    /**
     * 活动简介
     */
    @Schema(description = "活动简介")
    @Size(min = 1,max = 200,message = "活动简介必须在1-200字符之间！")
    private String activityIntroduction;

    /**
     * 标题图片
     */
    @Schema(description = "标题图片")
    private Integer headerImage;
    /**
     * 活动部门
     */
    @Schema(description = "活动部门")
    @Size(min = 4,max = 4,message = "部门字符必须为4")
    private String activityDepartment;
    /**
     * 活动类型
     */
    @Schema(description = "活动类型")
    @Size(min = 1,max = 20,message = "活动类型必须在1-20字符之间！")
    private String activityType;


    /**
     * 活动状态
     */
    @Schema(description = "活动状态")
    private Boolean state;
    /**
     * 是否置顶
     */
    @Schema(description = "是否置顶")
    private Boolean isTop;
}
