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
import tyut.selab.common.annotation.AccessLogAnnotation;
import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.PageParam;
import tyut.selab.modular.service.IAppService;

/**
 * @ClassName: ForeignAppController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-07 01:10
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/foreign")
@Tag(name = "前台App相关")
@Slf4j
public class ForeignAppController {
    @Autowired
    private IAppService iAppService;
    /**
     * 获取App信息
     * @return
     */
    @PostMapping("/getApp")
//    @AccessLogAnnotation()
    @Operation(summary = "获取App信息",description = "获取App信息")
    public R getApp(){
        return iAppService.getApp();
    }
}
