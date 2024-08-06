package tyut.selab.framework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.dto.param.LogParam;
import tyut.selab.framework.domain.dto.param.ResourceParam;
import tyut.selab.framework.service.ILogService;

/**
 * @ClassName: LogController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-23 10:37
 * @Version: 1.0
 **/
@RestController
@Tag(name = "日志管理")
@RequestMapping("/background/log")
@Slf4j
public class LogController {
    @Autowired
    private ILogService iLogService;

    @PostMapping("/getLoginLogList")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "获取登陆日志列表",description ="")
    public R getLoginLogList(@RequestBody @Validated LogParam logParam){
        return iLogService.getLoginLogList(logParam);
    }

    @PostMapping("/getSysLogList")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "获取系统日志列表",description ="")
    public R getSysLogList(@RequestBody @Validated LogParam logParam){
        return iLogService.getSysLogList(logParam);
    }


    @PostMapping("/getAccessLogList")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "获取访问日志列表",description ="")
    public R getAccessLogList(@RequestBody @Validated LogParam logParam){
        return iLogService.getAccessLogList(logParam);
    }
}
