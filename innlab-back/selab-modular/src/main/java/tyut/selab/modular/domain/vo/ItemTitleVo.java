package tyut.selab.modular.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tyut.selab.common.utils.EnumUtils;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.modular.domain.entity.ItemEntity;

/**
 * @ClassName: ItemVo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-24 16:51
 * @Version: 1.0
 **/
@Data
public class ItemTitleVo {

    private Integer itemId;
    /**
     * 项目标题
     */
    private String itemTitle;
    /**
     * 部门
     */
    private String department;
    /**
     * 项目简介
     */
    private String itemIntroduction;
    /**
     * 标题图片
     */
    private ImageVo headerImage;
    /**
     * 项目状态
     */
    private Boolean state;
    /**
     * 是否置顶
     */
    private Boolean isTop;


    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "作者")
    private String author;

    public ItemTitleVo(ItemEntity itemEntity){
        this.itemId = itemEntity.getItemId();
        this.author = itemEntity.getCreateUser();
        this.itemTitle = itemEntity.getItemTitle();
        this.itemIntroduction = itemEntity.getItemIntroduction();
        if (ObjectUtils.isNotNull(itemEntity.getDepartmentId())){
            this.department = EnumUtils.getDepartmentNameById(String.valueOf(itemEntity.getDepartmentId()));
        }
        this.isTop = itemEntity.getIsTop();
        this.state = itemEntity.getState();
        this.createTime = itemEntity.getCreateTime();
    }
}
