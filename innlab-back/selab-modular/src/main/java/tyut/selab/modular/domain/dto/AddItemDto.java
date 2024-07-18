package tyut.selab.modular.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: AddItemDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-17 14:45
 * @Version: 1.0
 **/
@Data
@Schema(description = "添加项目DTO")
public class AddItemDto {
    /**
     * 部门
     */
    @Schema(description = "部门")
    @NotNull(message = "部门不能为空！")
    @Size(min = 4,max = 4,message = "部门字符必须为4")
    private String department;
    /**
     * 项目标题
     */
    @Schema(description = "项目标题")
    @NotNull(message = "项目标题不能为空！")
    @Size(min = 1,max = 30,message = "标题最长30字符")
    private String itemTitle;
    /**
     * 项目简介
     */
    @Schema(description = "项目简介")
    @NotNull(message = "项目简介不能为空！")
    @Size(min = 1,max = 200,message = "项目简介最长200字符")
    private String itemIntroduction;
    /**
     * 标题图片
     */
    @Schema(description = "标题图片id")
    private Integer headerImage;
}
