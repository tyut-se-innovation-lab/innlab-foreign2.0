package tyut.selab.modular.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: UpdateItemDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-17 15:22
 * @Version: 1.0
 **/
@Data
public class UpdateItemDto {

    /**
     * 项目id
     */
    @NotNull(message = "项目id不能为空")
    private Integer itemId;
    /**
     * 部门
     */
    @Schema(description = "部门")
    @Size(min = 4,max = 4,message = "部门字符必须为4")
    private String department;
    /**
     * 项目标题
     */
    @Size(min = 1,max = 30,message = "标题在1~30字符之间")
    private String itemTitle;
    /**
     * 项目简介
     */
    @Size(min = 1,max = 200,message = "项目简介在1~200字符之间")
    private String itemIntroduction;
    /**
     * 标题图片
     */
    @Schema(description = "标题图片id")
    private Integer headerImage;
    /**
     * 项目markdown
     */
    @Schema(description = "项目markdown")
    @Size(min = 1,max = 65535,message = "项目markdown最长65535字符")
    private String contentMarkdown;
    /**
     * 项目状态
     */
    @Schema(description = "项目状态")
    private Boolean state;
    /**
     * 项目地址
     */
    @Schema(description = "项目url")
    @Size(min = 1,max = 200,message = "项目url在1~200字符之间")
    private String githubUrl;
    /**
     * 是否置顶
     */
    @Schema(description = "是否置顶")
    private Boolean isTop;
}
