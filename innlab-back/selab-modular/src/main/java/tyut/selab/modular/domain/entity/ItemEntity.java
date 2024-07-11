/**
 * Copyright (C), 2000-2024, XXX有限公司
 * FileName: ProjectEntity
 * Author: 22932
 * Date: 2024/3/4 20:15:17
 * Description:
 * <p>
 * History:
 * <author> 作者姓名
 * <time> 修改时间
 * <version> 版本号
 * <desc> 版本描述
 */
package tyut.selab.modular.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import tyut.selab.common.domain.BaseEntity;

/**
 * @ClassName: ProjectEntity
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2024/3/4 20:15:17
 */
@TableName("tyut_item")
@Data
@AllArgsConstructor
@Schema(description = "项目")
public class ItemEntity extends BaseEntity{
    /**
     * 项目id
     */
    @TableId(value = "item_id",type = IdType.AUTO)
    private Integer itemId;
    /**
     * 部门标题
     */
    @TableField("department_id")
    private Integer departmentId;
    /**
     * 项目标题
     */
    @TableField("item_title")
    private String itemTitle;
    /**
     * 项目简介
     */
    @TableField("item_introduction")
    private String itemIntroduction;
    /**
     * 标题图片
     */
    @TableField("header_image")
    private Integer headerImage;
    /**
     * 项目markdown
     */
    @TableField("content_markdown")
    private Integer contentMarkdown;
    /**
     * 项目状态
     */
    @TableField("state")
    private Integer state;
    /**
     * 项目地址
     */
    @TableField("github_url")
    private String githubUrl;
    /**
     * 是否置顶
     */
    @TableField("is_top")
    private Boolean isTop;
}

