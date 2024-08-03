package tyut.selab.modular.domain.dto.param;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tyut.selab.framework.domain.PageParam;

/**
 * @ClassName: ActivityParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-11 13:02
 * @Version: 1.0
 **/@Data
@Schema(description = "活动Param")

public class ActivityParam  extends PageParam {
    /**
     * 活动类型
     */
    @Schema(description = "活动类型")
    private String activityType;
    /**
     * 部门
     */
    @Schema(description = "部门")
    @Size(min = 4,max = 4,message = "部门字符必须为4")
    private String department;
}
