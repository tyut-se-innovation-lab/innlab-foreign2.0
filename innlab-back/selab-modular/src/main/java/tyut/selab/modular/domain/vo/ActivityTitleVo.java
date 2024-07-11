package tyut.selab.modular.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
    @Schema(description = "标题图片")
    private ImageVo headerImage;
    /**
     * 浏览量
     */
    @Schema(description = "浏览量")
    private Integer pageView;
    @Schema(description = "创建时间")
    private String createTime;

}
