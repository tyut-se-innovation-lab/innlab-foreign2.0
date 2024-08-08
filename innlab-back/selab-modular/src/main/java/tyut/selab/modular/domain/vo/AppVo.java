package tyut.selab.modular.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: AppVo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-07 01:14
 * @Version: 1.0
 **/
@Data
public class AppVo {

    @Schema(description = "版本名")
    private String versionName;

    @Schema(description = "版本号")
    private String versionNumber;

    @Schema(description = "更新内容")
    private String updateContent;

    @Schema(description = "更新直链")
    private String updateUrl;

    @Schema(description = "是否更新")
    private Boolean whetherUpdate ;
}
