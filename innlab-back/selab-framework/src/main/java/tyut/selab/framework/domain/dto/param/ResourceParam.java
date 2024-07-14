package tyut.selab.framework.domain.dto.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: ResourceParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-14 21:41
 * @Version: 1.0
 **/
@Data
public class ResourceParam extends PageParam{

    @Schema(description = "资源类型")
    private Integer resourceType;
}
