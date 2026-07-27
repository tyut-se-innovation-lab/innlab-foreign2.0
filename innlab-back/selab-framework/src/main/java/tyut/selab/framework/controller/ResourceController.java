package tyut.selab.framework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tyut.selab.common.annotation.AccessLogAnnotation;
import tyut.selab.common.annotation.SysLogAnnotation;
import tyut.selab.common.annotation.WhitelistAnnotation;
import tyut.selab.common.domain.Lz;
import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.dto.param.ResourceParam;
import tyut.selab.framework.domain.vo.CookieVo;
import tyut.selab.framework.service.IResourceService;

import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName: ResourceController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-23 16:40
 * @Version: 1.0
 **/
@RestController
@Tag(name = "资源管理")
@RequestMapping("/background/resource")
@Slf4j
public class ResourceController {
    @Autowired
    private IResourceService iResourceService;
//    @PostMapping("/cachexookie")
//    @PreAuthorize("@ss.hasPermission('admin')")
//    @Operation(summary = "缓存蓝奏云Cookie",description = "缓存蓝奏云Cookie")
//    public R cacheCookie(@RequestBody @Validated CookieVo cookieVo){
//        Integer folderId = Integer.valueOf(cookieVo.getFolderId());
//        return iResourceService.cacheCookie(cookieVo);
//    }

    @Operation(summary = "添加图片",description ="添加资源仅支持.jpg.png 文件，调用本接口后需根据返回信息调用添加资源接口")
    @Parameter(name="file",description="上传文件",required=true)
    @Parameter(name="description",description="文件描述")
    @PostMapping("/addImage")
    @SysLogAnnotation(operModul = "资源管理",operType = "新增",operDesc = "添加图片")
    public R handleFileUploadImage(MultipartFile file,String description){
        return iResourceService.addResource(file,description,1);
    }
    @PostMapping("/addvideo")
    @Operation(summary = "添加视频",description ="添加资源仅支持.mp4文件，调用本接口后需根据返回信息调用添加资源接口")
    @SysLogAnnotation(operModul = "资源管理",operType = "新增",operDesc = "添加视频")
    @Parameter(name="file",description="上传文件",required=true)
    @Parameter(name="description",description="文件描述")
    public R handleFileUploadVideo(MultipartFile file,String description){
        return iResourceService.addResource(file,description,2);
    }
    @Operation(summary = "添加文件",description ="添加资源，调用本接口后需根据返回信息调用添加资源接口")
    @Parameter(name="file",description="上传文件",required=true)
    @Parameter(name="description",description="文件描述")
    @PostMapping("/addResource")
    @SysLogAnnotation(operModul = "资源管理",operType = "新增",operDesc = "添加文件")
    public R handleFileUploadResource(MultipartFile file,String description){
        return iResourceService.addResource2(file,description,3);
    }

    @PostMapping("/addPdf")
    @Operation(summary = "添加PdF",description ="添加资源仅支持.pdf文件，调用本接口后需根据返回信息调用添加资源接口")
    @SysLogAnnotation(operModul = "资源管理",operType = "新增",operDesc = "添加视频")
    @Parameter(name="file",description="上传文件",required=true)
    @Parameter(name="description",description="文件描述")
    public R handleFileUploadPdf(MultipartFile file,String description){
        return iResourceService.addResource2(file,description,4);
    }

    @PostMapping("/addMp3")
    @Operation(summary = "添加MP3",description ="添加资源仅支持.mp3文件，调用本接口后需根据返回信息调用添加资源接口")
    @SysLogAnnotation(operModul = "资源管理",operType = "新增",operDesc = "添加视频")
    @Parameter(name="file",description="上传文件",required=true)
    @Parameter(name="description",description="文件描述")
    public R handleFileUploadMp3(MultipartFile file,String description){
        return iResourceService.addResource2(file,description,5);
    }


    @PostMapping("/resourceList")
    @Operation(summary = "资源列表",description ="获取资源列表方便管理")
    public R getResourceList(@RequestBody @Validated ResourceParam resourceParam){
        return iResourceService.getResourcelist(resourceParam);
    }

    @PostMapping("/getCacheImg")
    @Operation(summary = "获取缓存图片",description ="获取缓存图片，转图床使用")
    public R getCacheImg(){
        return iResourceService.getCacheImg2();
    }
    @PostMapping("/deleteAllCache")
    @Operation(summary = "删除所有缓存",description ="删除所有缓存")
    public R deleteAllCache(){
        return iResourceService.deleteAllCache();
    }
    @PostMapping("/wyImgUpload")
    @Operation(summary = "微云图床上传",description ="微云图床上传")
    public R wyImgUpload(@RequestBody Set<String> urls){
        return iResourceService.addWYImg(urls);
    }

    @PostMapping("/getResourceByLz")
    @Operation(summary = "通过蓝奏云三段获取资源")
    @AccessLogAnnotation()
    @WhitelistAnnotation()
    public R getResourceByLz(@RequestBody @Validated Lz lz) throws ExecutionException, InterruptedException {
        return R.success("获取成功！",iResourceService.getResourceByLz(lz).get());
//        return iResourceService.getResourceByLz2(lz);
    }
}
