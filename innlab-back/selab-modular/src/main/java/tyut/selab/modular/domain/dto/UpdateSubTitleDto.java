package tyut.selab.modular.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: UpdateSubTitleDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-02 23:54
 * @Version: 1.0
 **/
@Data
@Schema(description = "修改小标题DTO")
public class UpdateSubTitleDto {
    /**
     * 小标题id
     */
    @TableId(value = "subtitle_id",type = IdType.AUTO)
    @Schema(description = "小标题id")
    @NotNull(message = "小标题id不能为空")
    private Integer subtitleId;
    /**
     * 小标题名
     */
    @TableField("subtitle_name")
    @Schema(description = "小标题名")
    @Size(max = 20,message = "小标题名要求0~20字符")
    private String subtitleName;
    /**
     * 小标题内容
     */
    @TableField("subtitle_content")
    @Schema(description = "小标题内容")
    private String subtitleContent;
    /**
     * 小标题内容
     */
    @Schema(description = "小标题移动")
    @Min(value = -1,message = "小标题移动格式错误！")
    @Max(value = 1,message = "小标题移动格式错误！")
    private Integer subtitleMove;
}
