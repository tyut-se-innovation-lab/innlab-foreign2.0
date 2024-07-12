package tyut.selab.modular.controller.foreign;

import groovy.util.logging.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tyut.selab.common.domain.R;
import tyut.selab.modular.domain.dto.param.ItemParam;
import tyut.selab.modular.service.IItemService;

/**
 * @ClassName: ItemController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-24 17:44
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/foreign")
@Tag(name = "前台项目展示")
@Slf4j
public class ItemController {
    @Autowired
    private IItemService iItemService;

    /**
     * 展示方向和项目介绍
     * @return R
     */
    @PostMapping("/showItems")
    @Operation(summary = "展示方向介绍和项目介绍")
    public R showItems(@RequestBody @Validated ItemParam itemParam){
        return iItemService.showItemsTitle2(itemParam);
    }

    /**
     * 展示项目标题
     * @return R
     */
    @PostMapping("/showItemsTitle")
    @Operation(summary = "展示项目介绍标题")
    public R showItemsTitle(@RequestBody @Validated ItemParam itemParam){
        return iItemService.showItemsTitle1(itemParam);
    }

    /**
     * 进入某一个内容
     * @param itemId 项目id
     * @return R
     */
    @PostMapping("/getOneItem")
    @Operation(summary = "进入某一个内容")
    @Parameter(name="itemId",description="项目id",required=true)
    public R getOneItem(@RequestParam("itemId") String itemId){
        return iItemService.getItemMsg(Integer.valueOf(itemId));
    }
}
