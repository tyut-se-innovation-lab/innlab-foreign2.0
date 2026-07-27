package tyut.selab.framework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tyut.selab.common.annotation.AccessLogAnnotation;
import tyut.selab.common.annotation.WhitelistAnnotation;
import tyut.selab.common.domain.Lz;
import tyut.selab.framework.service.IResourceService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName: ForeignController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2026-02-27 14:54
 * @Version: 1.0
 **/

@RestController
@Tag(name = "对外接口管理")
@Slf4j
@RequestMapping("/foreign")
public class ForeignController {

    @Autowired
    private IResourceService iResourceService;


    @GetMapping("/getResource")
    @Operation(summary = "通过蓝奏云三段获取资源")
    @AccessLogAnnotation()
    @WhitelistAnnotation(method = RequestMethod.GET)
    public void getResourceByLz(
            @RequestParam String isNewd,
            @RequestParam String fId,
            @RequestParam String pwd,
            HttpServletResponse response
    ) throws ExecutionException, InterruptedException, IOException {
        Lz lz = new Lz(pwd,fId,isNewd);
        String url = iResourceService.getResourceByLz(lz).get();

        // 直接重定向
        response.sendRedirect(url);
    }


}
