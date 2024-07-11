package tyut.selab.modular.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tyut.selab.common.domain.BaseEntity;

@Data
@TableName("tyut_personnel")
@Schema(description = "人员介绍实体类")
public class PersonnelEntity extends BaseEntity {
    /**
     * 人员ID
     */
    @TableId(value = "personnel_id", type = IdType.AUTO)
    private Integer personnelId;
    /**
     * 隶属部门
     */
    @TableField(value = "department_id")
    @Schema(description = "人员所属部门")
    @NotBlank(message = "所属部门不能为空")
    private Integer personnelDepartment;
    /**
     * 人员姓名
     */
    @TableField(value = "personnel_name")
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
    @NotBlank(message = "人员年代不能为空")
    @Schema(description = "人员年代")
    @Size(min = 4, max = 4, message = "年代长度为必须为4 ！")
    @TableField(value = "personnel_period")
    private String personnelPeriod;
    /**
     * 职位
     */
    @TableField(value = "personnel_posts")
    @Schema(description = "人员职位")
    @NotBlank(message = "人员职位不能为空")
    @Size(min = 1, max = 30, message = "人员职位在1-30字符之间!")
    private String personnelPost;
    /**
     * 人员贡献
     */
    @Schema(description = "人员事例")
    @Size(min = 1, max = 200, message = "贡献描述不能超过200字")
    @TableField(value = "personnel_example")
    private String personnelExample;

    @Schema(description = "人员语录")
    @Size(min = 1, max = 100, message = "语录不能超过200字")
    @TableField(value = "personnel_saying")
    private String personnelSaying;
    /**
     * 权重（默认1）
     */
    @TableField(value = "personnel_sort")
    @Schema(description = "人员展示权重")
    private Integer personnelSort;

    /**
     * 人员状态（1保留/0停用）
     */
    @TableField(value = "state")
    private Boolean state;
}
