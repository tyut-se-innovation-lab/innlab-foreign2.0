package tyut.selab.modular.controller.background;

import groovy.util.logging.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tyut.selab.common.annotation.SysLogAnnotation;
import tyut.selab.common.domain.R;
import tyut.selab.modular.domain.dto.AddItemDto;
import tyut.selab.modular.domain.dto.UpdateItemDto;
import tyut.selab.modular.domain.dto.param.ItemParam;
import tyut.selab.modular.service.IItemService;

/**
 * @ClassName: ItemController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-17 14:30
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/background/item")
@Tag(name = "项目管理")
@Slf4j
public class ItemController {
    @Autowired
    private IItemService iItemService;



    @SysLogAnnotation(operModul = "项目管理",operType = "新增",operDesc = "新增项目")
    @PostMapping("/addItem")
    @Operation(summary = "添加项目介绍")
    public R addItem(@RequestBody @Validated AddItemDto addItemDto){
        return iItemService.addItem(addItemDto);
    }
    @PostMapping("/getItemList")
    @Operation(summary = "获取项目列表")
    public R getItemlist(@RequestBody @Validated ItemParam itemParam){
        return iItemService.getItemList(itemParam);
    }

    @SysLogAnnotation(operModul = "项目管理",operType = "修改",operDesc = "修改项目")
    @PostMapping("/updateItem")
    @Operation(summary = "修改项目")
    public R updateItem(@RequestBody @Validated UpdateItemDto updateItemDto){
        return iItemService.updateItem(updateItemDto);
    }

    @PostMapping("/getItemMsg")
    @Operation(summary = "获取项目详细信息")
    public R getItemMsgById(@RequestParam("itemId") Integer itemId){
        return iItemService.getItemMsg1(itemId);
    }

    @SysLogAnnotation(operModul = "项目管理",operType = "删除",operDesc = "删除项目")
    @PostMapping("/deleteItem")
    @Operation(summary = "删除项目")
    public R deleteItemById(@RequestParam("itemId") Integer itemId){
        return iItemService.deleteItemById(itemId);
    }
}
