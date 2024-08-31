package tyut.selab.modular.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * @ClassName: AddPersonalDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-22 18:30
 * @Version: 1.0
 **/
@Data
@Schema(description = "添加人员DTO")
public class AddPersonalDto {

    /**
     * 隶属部门
     */
    @Schema(description = "人员所属部门")
    @NotBlank(message = "所属部门不能为空")
    @Size(min = 4,max = 4,message = "部门字符必须为4")
    private String personnelDepartment;
    /**
     * 人员姓名
     */
    @NotBlank(message = "人员姓名不能为空")
    @Schema(description = "人员姓名")
    @Size(min = 1, max = 20, message = "人员姓名必须在1~20字符之间")
    private String personnelName;
    /**
     * 人员头像
     */
    @TableField(value = "personnel_avatar")
    private Integer personnelAvatar;
    /**
     * 年代（哪一届）
     */
    @NotNull(message = "人员年代不能为空")
    @Schema(description = "人员年代")
    @Min(value = 2017, message = "年代最低为2017年")
    @Max(value = 2025, message = "年代最后高为2025年")
    private Integer personnelPeriod;
    /**
     * 职位
     */
    @Schema(description = "人员职位")
    @NotBlank(message = "人员职位不能为空")
    @Size(min = 1, max = 30, message = "人员职位在1-30字符之间!")
    private String personnelPost;
    /**
     * 人员贡献
     */
    @Schema(description = "人员事例")
    @Size(min = 1, max = 200, message = "贡献描述不能超过200字")
    private String personnelExample;

    @Schema(description = "人员语录")
    @Size(min = 1, max = 100, message = "语录不能超过200字")
    private String personnelSaying;
    /**
     * 权重（默认1）
     */
    @Schema(description = "人员展示权重")
    @Max(value = 9,message = "权重异常！")
    private Integer personnelSort;
}
