package tyut.selab.modular.service;

import tyut.selab.common.domain.R;
import tyut.selab.modular.domain.dto.AddActivityDto;
import tyut.selab.modular.domain.dto.AddSubTitleDto;
import tyut.selab.modular.domain.dto.UpdateActivityDto;
import tyut.selab.modular.domain.dto.UpdateSubTitleDto;
import tyut.selab.modular.domain.dto.param.ActivityParam;

/**
 * @ClassName: ActivityService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-11 12:57
 * @Version: 1.0
 **/
public interface IActivityService {
    R shwoActivityTitle1(ActivityParam activityParam);

    R shwoActivityTitle2(ActivityParam activityParam);

    R getActivityMsg(Integer activityId);

    R getActivityList(ActivityParam activityParam);

    R addActivity(AddActivityDto addActivityDto);

    R deleteActivity(Integer activityId);

    R updateActivity(UpdateActivityDto updateActivityDto);

    R addSubTitle(AddSubTitleDto addSubTitleDto);

    R updateSubTitle(UpdateSubTitleDto updateSubTitleDto);

    R deleteSubTitleById(Integer subTitleId);

    R getSubTitleMsg(Integer subTitleId);

    R getActivityMsg1(Integer activityId);
}
