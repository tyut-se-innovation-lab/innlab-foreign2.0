package tyut.selab.modular.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tyut.selab.common.utils.EnumUtils;
import tyut.selab.modular.domain.entity.ItemEntity;

/**
 * @ClassName: ItemMsgVo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-24 17:04
 * @Version: 1.0
 **/
@Data
public class ItemMsgVo {
    private Integer itemId;
    /**
     * 项目标题
     */
    private String itemTitle;
    /**
     * 项目简介
     */
    private String itemIntroduction;
    /**
     * 标题图片
     */
    private ImageVo headerImage;
    /**
     * 项目部门
     */
    private String itemDepartment;

    @Schema(description = "markdownBase64")
    private String markdownBase64;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "更新时间")
    private String updateTime;

    @Schema(description = "审核人")
    private String auditor;

    public ItemMsgVo(ItemEntity itemEntity){
        this.itemId = itemEntity.getItemId();
        this.itemTitle = itemEntity.getItemTitle();
        this.itemIntroduction = itemEntity.getItemIntroduction();
        this.createTime = itemEntity.getCreateTime();
        this.updateTime = itemEntity.getUpdateTime();
        this.auditor = itemEntity.getUpdateUser();
        this.author = itemEntity.getCreateUser();
        this.itemDepartment = EnumUtils.getDepartmentNameById(String.valueOf(itemEntity.getDepartmentId()));
    }
}
