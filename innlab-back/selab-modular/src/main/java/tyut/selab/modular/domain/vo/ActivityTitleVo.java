package tyut.selab.modular.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tyut.selab.common.utils.EnumUtils;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.modular.domain.entity.ActivityEntity;

/**
 * @ClassName: ActivityVo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-11 13:10
 * @Version: 1.0
 **/
@Data
public class ActivityTitleVo {
    /**
     * 活动id
     */
    @Schema(description = "活动id")
    private Integer activityId;
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
     * 标题图片
     */
    @Schema(description = "标题图片id")
    private Integer headerImageId;
    /**
     * 标题图片
     */
    @Schema(description = "标题图片")
    private ImageVo headerImage;
    /**
     * 浏览量
     */
    @Schema(description = "浏览量")
    private Integer pageView;

    /**
     * 活动部门
     */
    @Schema(description = "活动部门")
    private String activityDepartment;
    /**
     * 活动类型
     */
    @Schema(description = "活动类型")
    private String activityType;

    @Schema(description = "创建时间")
    private String createTime;

    /**
     * 活动状态
     */
    private Boolean state;
    /**
     * 活动置顶
     */
    private Boolean isTop;

    public ActivityTitleVo(ActivityEntity activityEntity){
        this.activityId = activityEntity.getActivityId();
        this.activityType = activityEntity.getActivityType();
        this.activityTitle = activityEntity.getActivityTitle();
        this.activityIntroduction = activityEntity.getActivityIntroduction();
        this.state = activityEntity.getState();
        this.isTop = activityEntity.getIsTop();
        this.createTime = activityEntity.getCreateTime();
        this.pageView = activityEntity.getPageView();
        if (ObjectUtils.isNotNull(activityEntity.getActivityDepartment())){
            this.activityDepartment = EnumUtils.getDepartmentNameById(String.valueOf(activityEntity.getActivityDepartment()));
        }
    }
}
