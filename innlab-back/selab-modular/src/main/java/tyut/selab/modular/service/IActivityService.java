package tyut.selab.modular.service;

import tyut.selab.common.domain.R;
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
}
