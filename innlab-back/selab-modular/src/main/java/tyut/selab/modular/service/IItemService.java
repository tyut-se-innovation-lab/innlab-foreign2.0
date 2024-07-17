package tyut.selab.modular.service;

import tyut.selab.common.domain.R;
import tyut.selab.modular.domain.dto.AddItemDto;
import tyut.selab.modular.domain.dto.UpdateItemDto;
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

    R getItemMsg1(Integer itemId);

    R addItem(AddItemDto addItemDto);

    R getItemList(ItemParam itemParam);

    R updateItem(UpdateItemDto updateItemDto);

    R deleteItemById(Integer itemId);
}
