package tyut.selab.modular.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tyut.selab.common.domain.BaseEntity;

/**
 * @ClassName: ActivityEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-11 12:49
 * @Version: 1.0
 **/
@TableName("tyut_activity")
@Schema(description = "日常活动")
@Data
public class ActivityEntity extends BaseEntity {
    /**
     * 活动id
     */
    @TableId(value = "activity_id",type = IdType.AUTO)
    private Integer activityId;
    /**
     * 活动标题
     */
    @TableField("activity_title")
    private String activityTitle;
    /**
     * 活动简介
     */
    @TableField("activity_introduction")
    private String activityIntroduction;

    @TableField("header_image")
    private Integer headerImage;
    /**
     * 浏览量
     */
    @TableField("page_view")
    private Integer pageView;

    /**
     * 活动状态
     */
    @TableField("state")
    private Boolean state;
    /**
     * 是否置顶
     */
    @TableField("is_top")
    private Boolean isTop;
    /**
     * 活动部门
     */
    @TableField("activity_department")
    private Integer activityDepartment;
    /**
     * 活动类型
     */
    @TableField("activity_type")
    private String activityType;
}
