package tyut.selab.modular.controller.background;

import groovy.util.logging.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tyut.selab.common.annotation.SysLogAnnotation;
import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.PageParam;
import tyut.selab.modular.domain.dto.AddHistoryDto;
import tyut.selab.modular.domain.dto.UpdateHistoryDto;
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
@Tag(name = "历史结点管理")
@Slf4j
public class HistoryController {
    @Autowired
    private IHistoryService iHistoryService;

    @SysLogAnnotation(operModul = "历史结点管理",operType = "新增",operDesc = "添加历史结点")
    @PostMapping("/addHistory")
    @Operation(summary = "添加历史结点")
    @PreAuthorize("@ss.hasPort('history:add')")
    public R addHistory(@RequestBody @Validated AddHistoryDto addHistoryDto){
        return iHistoryService.addHistory(addHistoryDto);
    }


    @SysLogAnnotation(operModul = "历史结点管理",operType = "修改",operDesc = "修改历史结点")
    @PostMapping("/updateHistory")
    @Operation(summary = "修改历史结点")
    @PreAuthorize("@ss.hasPort('history:update')")
    public R updateHistory(@RequestBody @Validated UpdateHistoryDto updateHistoryDto){
        return iHistoryService.updateHistory(updateHistoryDto);
    }

    @PostMapping("/historyList")
    @Operation(summary = "获取历史结点列表")
    public R historyList(@RequestBody @Validated PageParam pageParam){
        return iHistoryService.getHistoryList(pageParam);
    }

    @SysLogAnnotation(operModul = "历史结点管理",operType = "删除",operDesc = "删除历史结点")
    @PostMapping("/deleteHistory")
    @PreAuthorize("@ss.hasPort('history:delete')")
    @Operation(summary = "删除历史结点")
    @Parameter(name="historyId",description="历史结点Id",required=true)
    public R deleteHistory(@RequestParam("historyId")Integer historyId){
        return iHistoryService.deleteHistoryById(historyId);
    }

}
