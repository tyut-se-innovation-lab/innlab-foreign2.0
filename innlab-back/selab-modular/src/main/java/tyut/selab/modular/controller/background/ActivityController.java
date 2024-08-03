package tyut.selab.modular.controller.background;

import groovy.util.logging.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tyut.selab.common.annotation.SysLogAnnotation;
import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.PageParam;
import tyut.selab.modular.domain.dto.*;
import tyut.selab.modular.domain.dto.param.ActivityParam;
import tyut.selab.modular.service.IActivityService;

/**
 * @ClassName: ActivityController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-17 14:31
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/background/activity")
@Tag(name = "机构动态管理")
@Slf4j
public class ActivityController {
    @Autowired
    private IActivityService iActivityService;
    @PostMapping("/addActivity")
    @Operation(summary = "添加机构动态")
    public R addActivity(@RequestBody @Validated AddActivityDto addActivityDto){
        return iActivityService.addActivity(addActivityDto);
    }
    @PostMapping("/activityList")
    @Operation(summary = "获取机构动态列表")
    public R activityList(@RequestBody @Validated ActivityParam activityParam){
        return iActivityService.getActivityList(activityParam);
    }
    @PostMapping("/updateActivity")
    @Operation(summary = "修改机构动态")
    public R updateActivity(@RequestBody @Validated UpdateActivityDto updateActivityDto){
        return iActivityService.updateActivity(updateActivityDto);
    }
    @PostMapping("/deleteActivity")
    @Operation(summary = "删除机构动态")
    public R deleteActivity(@RequestParam("activityId")Integer activityId){
        return iActivityService.deleteActivity(activityId);
    }

    @PostMapping("/addSubTitle")
    @Operation(summary = "添加小标题")
    public R addSubTitle(@RequestBody @Validated AddSubTitleDto addSubTitleDto){
        return iActivityService.addSubTitle(addSubTitleDto);
    }

    @PostMapping("/updateSubTitle")
    @Operation(summary = "修改小标题")
    public R updateSubTitle(@RequestBody @Validated UpdateSubTitleDto updateSubTitleDto){
        return iActivityService.updateSubTitle(updateSubTitleDto);
    }
    @PostMapping("/getActivityMsg1")
    @Operation(summary = "获取活动内容")
    public R getActivityMsg1(@RequestParam("activityId")Integer activityId){
        return iActivityService.getActivityMsg1(activityId);
    }
    @PostMapping("/deleteSubTitle")
    @Operation(summary = "删除小标题")
    public R deleteSubTitleById(@RequestParam("subTitleId")Integer subTitleId){
        return iActivityService.deleteSubTitleById(subTitleId);
    }

}
