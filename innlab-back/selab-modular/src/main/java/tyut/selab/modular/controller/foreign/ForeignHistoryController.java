package tyut.selab.modular.controller.foreign;

import groovy.util.logging.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.PageParam;
import tyut.selab.modular.service.IHistoryService;

/**
 * @ClassName: HistoryController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-27 17:49
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/foreign")
@Tag(name = "前台组织历史展示")
@Slf4j
public class ForeignHistoryController {
    @Autowired
    private IHistoryService iHistoryService;

    /**
     * 前台获取组织历史
     * @return
     */
    @PostMapping("/showHistory")
    @Operation(summary = "获取历史信息",description = "获取历史信息")
    public R showHistory(@RequestBody @Validated PageParam pageParam){
        return iHistoryService.getHistoryForeign(pageParam);
    }

}
