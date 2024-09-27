package tyut.selab.modular.controller.foreign;

import groovy.util.logging.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tyut.selab.common.annotation.AccessLogAnnotation;
import tyut.selab.common.domain.Lz;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.http.HttpClientUtils;
import tyut.selab.framework.domain.PageParam;
import tyut.selab.framework.service.IResourceService;
import tyut.selab.modular.service.IAppService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

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
    @Autowired
    private IResourceService iResourceService;
    /**
     * 获取App信息
     * @return
     */
    @PostMapping("/getGPT")
//    @AccessLogAnnotation()
    @Operation(summary = "获取App信息",description = "获取App信息")
    public R getApp(@RequestParam("msg") String msg){
        return R.success(HttpClientUtils.get("http://tool.xwm.lol/api.php?msg="+msg));
    }



    @GetMapping(value="/lz")
    @Parameter(name="resourceId",description="资源id",required=true)
    public void test(@RequestParam("resourceId") Integer resourceId,HttpServletResponse response) throws IOException {
        R r = iResourceService.getResourceLz(resourceId);
        Lz lz = (Lz) r.getData();
        R r1 = iResourceService.getResourceByLz2(lz);
    String url = (String) r1.getMessage();
        response.sendRedirect(url);
    }
}
