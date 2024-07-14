package tyut.selab.modular.controller.background;

import groovy.util.logging.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.PageParam;
import tyut.selab.modular.domain.dto.HistoryDto;
import tyut.selab.modular.service.IHistoryService;

/**
 * @ClassName: HistoryController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-14 20:44
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/background/history")
@Tag(name = "前台日常活动")
@Slf4j
public class HistoryController {
    @Autowired
    private IHistoryService iHistoryService;

    @PostMapping("/addHistory")
    @Operation(summary = "添加历史结点")
    public R addHistory(@RequestBody @Validated(HistoryDto.Add.class) HistoryDto historyDto){
        return iHistoryService.addHistory(historyDto);
    }


    @PostMapping("/updateHistory")
    @Operation(summary = "修改历史结点")
    public R updateHistory(@RequestBody @Validated(HistoryDto.Update.class) HistoryDto historyDto){
        return iHistoryService.updateHistory(historyDto);
    }

    @PostMapping("/historyList")
    @Operation(summary = "获取历史结点列表")
    public R historyList(@RequestBody @Validated PageParam pageParam){
        return iHistoryService.getHistoryList(pageParam);
    }

    @PostMapping("/deleteHistory")
    @Operation(summary = "删除历史结点")
    @Parameter(name="historyId",description="历史结点Id",required=true)
    public R deleteHistory(@RequestParam("historyId")Integer historyId){
        return iHistoryService.deleteHistoryById(historyId);
    }

}
