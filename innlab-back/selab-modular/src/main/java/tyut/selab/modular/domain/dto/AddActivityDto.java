package tyut.selab.modular.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: AddActivityDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-23 13:05
 * @Version: 1.0
 **/
@Data
@Schema(description = "添加活动DTO")
public class AddActivityDto {

    /**
     * 活动标题
     */
    @Schema(description = "活动标题")
    @NotNull(message = "活动标题不能为null")
    @Size(min = 1,max = 30,message = "活动标题必须在1-30字符之间！")
    private String activityTitle;
    /**
     * 活动简介
     */
    @Schema(description = "活动简介")
    @NotNull(message = "活动简介不能为null")
    @Size(min = 1,max = 200,message = "活动简介必须在1-200字符之间！")
    private String activityIntroduction;

    /**
     * 标题图片
     */
    @Schema(description = "标题图片")
    @NotNull(message = "标题图片介不能为null")
    private Integer headerImage;
    /**
     * 活动部门
     */
    @Schema(description = "活动部门")
    @NotNull(message = "活动部门介不能为null")
    @Size(min = 4,max = 4,message = "部门字符必须为4")
    private String activityDepartment;
    /**
     * 活动类型
     */
    @NotNull(message = "活动类型不能为null")
    @Size(min = 1,max = 20,message = "活动类型必须在1-20字符之间！")
    private String activityType;
}
