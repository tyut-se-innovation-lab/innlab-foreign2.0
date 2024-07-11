package tyut.selab.modular.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tyut.selab.modular.domain.entity.ActivityEntity;
import tyut.selab.modular.domain.model.SubTitleMo;

import java.util.List;

/**
 * @ClassName: ActivityMsgVo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-11 13:30
 * @Version: 1.0
 **/
@Data
@Schema(description = "活动详细信息")
public class ActivityMsgVo {
    /**
     * 活动标题
     */
    @Schema(description = "活动标题")
    private String activityTitle;
    /**
     * 活动简介
     */
    @Schema(description = "活动简介")
    private String activityIntroduction;
    /**
     * 浏览量
     */
    @Schema(description = "浏览量")
    private Integer pageView;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "更新时间")
    private String updateTime;
    /**
     * 活动内容
     */
    @Schema(description = "活动内容")
    private List<SubTitleMo> activityContent;

    public ActivityMsgVo(ActivityEntity activityEntity){
        this.activityTitle = activityEntity.getActivityTitle();
        this.activityIntroduction = activityEntity.getActivityIntroduction();
        this.pageView = activityEntity.getPageView();
        this.createTime = activityEntity.getCreateTime();
        this.updateTime = activityEntity.getUpdateTime();
    }
}
