package tyut.selab.modular.service;

import tyut.selab.common.domain.R;
import tyut.selab.modular.domain.dto.param.ItemParam;

/**
 * @ClassName: ItemService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-24 16:45
 * @Version: 1.0
 **/
public interface IItemService {
    R showItemsTitle1(ItemParam itemParam);

    R showItemsTitle2(ItemParam itemParam);

    R getItemMsg(Integer itemId);
}
