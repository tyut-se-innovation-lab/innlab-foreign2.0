package tyut.selab.modular.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
     * 项目简介
     */
    private String itemIntroduction;
    /**
     * 标题图片
     */
    private ImageVo headerImage;
}
