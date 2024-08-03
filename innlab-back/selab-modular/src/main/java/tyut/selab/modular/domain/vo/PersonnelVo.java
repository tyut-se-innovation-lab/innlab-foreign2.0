package tyut.selab.modular.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tyut.selab.common.utils.EnumUtils;
import tyut.selab.modular.domain.entity.PersonnelEntity;

@Data
@Schema(description = "人员介绍")
public class PersonnelVo {
    /**
     * 人员姓名
     */
    @NotBlank(message = "人员id不能为空")
    @Schema(description = "人员id")
    private Integer personnelId;
    /**
     * 人员姓名
     */
    @NotBlank(message = "人员姓名不能为空")
    @Schema(description = "人员姓名")
    @Size(min = 1, max = 20, message = "人员姓名必须在1~20字符之间")
    private String personnelName;

    @Schema(description = "人员头像id")
    private Integer personnelAvatarId;
    /**
     * 人员头像
     */
    @Schema(description = "人员头像")
    private ImageVo personnelAvatar;
    /**
     * 年代（哪一届）
     */
    @NotBlank(message = "人员年代不能为空")
    @Schema(description = "人员年代")
    @Size(min = 4, max = 4, message = "年代长度为必须为4 ！")
    private String personnelPeriod;
    /**
     * 隶属部门
     */
    @Schema(description = "人员所属部门")
    private String personnelDepartment;
    /**
     * 职位
     */
    @Schema(description = "人员职位")
    @Size(min = 1, max = 30, message = "人员职位在1-30字符之间!")
    private String personnelPost;
    /**
     * 人员贡献
     */
    @Schema(description = "人员贡献")
    @Size(min = 1, max = 200, message = "贡献描述不能超过200字")
    private String personnelContribute;

    /**
     * 留下一段话
     */
    @Schema(description = "人员语录")
    @Size(min = 1, max = 100, message = "语录不能超过200字")
    private String personnelSaying;
    /**
     * 权重（默认1）
     */
//    @Schema(description = "人员展示权重")
//    @Size(min = 1, max = 1, message = "权重为1字符")
//    private String personnelOrder;
    /**
     * 人员状态（1保留/0停用）
     */
    @TableField(value = "state")
    private Boolean state;
    /**
     * 权重（默认1）
     */
    @TableField(value = "personnel_sort")
    @Schema(description = "人员展示权重")
    private Integer personnelSort;

    public PersonnelVo(PersonnelEntity personnelEntity){
        this.personnelName = personnelEntity.getPersonnelName();
        this.personnelContribute = personnelEntity.getPersonnelExample();
        this.personnelSaying = personnelEntity.getPersonnelSaying();
        this.personnelDepartment = EnumUtils.getDepartmentNameById(String.valueOf(personnelEntity.getPersonnelDepartment()));
        this.personnelPost = personnelEntity.getPersonnelPost();
        this.personnelPeriod = personnelEntity.getPersonnelPeriod();
        this.state = personnelEntity.getState();
        this.personnelSort = personnelEntity.getPersonnelSort();
    }
}
