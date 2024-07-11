package tyut.selab.modular.controller.foreign;

import groovy.util.logging.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.common.annotation.RequestSingleParam;
import tyut.selab.common.domain.R;
import tyut.selab.modular.domain.dto.param.ActivityParam;
import tyut.selab.modular.service.IActivityService;

/**
 * @ClassName: ActivityController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-11 14:13
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/foreign")
@Tag(name = "前台日常活动")
@Slf4j
public class ActivityController {
    @Autowired
    private IActivityService iActivityService;
    @PostMapping("/showActivitys")
    @Operation(summary = "展示日常活动")
    public R showActivitys(@RequestBody @Validated ActivityParam activityParam){
        return iActivityService.shwoActivityTitle2(activityParam);
    }
    @PostMapping("/showActivitysTitle")
    @Operation(summary = "展示日常活动标题")
    public R showActivitysTitle(@RequestBody @Validated ActivityParam activityParam){
        return iActivityService.shwoActivityTitle1(activityParam);
    }

    @PostMapping("/getActivity")
    @Operation(summary = "进入某一个内容")
    @Parameter(name="activityId",description="活动id",required=true)
    public R getOneActivity(@RequestSingleParam("activityId") String activityId){
        return iActivityService.getActivityMsg(Integer.valueOf(activityId));
    }
}
