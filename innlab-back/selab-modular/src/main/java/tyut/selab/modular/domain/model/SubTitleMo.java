package tyut.selab.modular.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tyut.selab.modular.domain.vo.ImageVo;

import java.util.List;

/**
 * @ClassName: SubTitleMo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-11 13:12
 * @Version: 1.0
 **/
@Data
public class SubTitleMo {
    /**
     * 小标题名
     */
    @Schema(description = "小标题名")
    private String subtitleName;
    /**
     * 小标题类型
     */
    @Schema(description = "小标题类型")
    private Integer subtitleType;
    /**
     * 小标题内容
     */
    @Schema(description = "小标题内容")
    private String subtitleContent;
    /**
     * 标题图片
     */
    @Schema(description = "资源")
    private List<ImageVo> resource;
}
