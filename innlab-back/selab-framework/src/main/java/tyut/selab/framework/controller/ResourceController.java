package tyut.selab.framework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.vo.CookieVo;
import tyut.selab.framework.service.IResourceService;

/**
 * @ClassName: ResourceController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-23 16:40
 * @Version: 1.0
 **/
@RestController
@Tag(name = "资源管理")
@RequestMapping("background/adm")
@Slf4j
public class ResourceController {
    @Autowired
    private IResourceService iResourceService;
    @PostMapping("/resource/cachexookie")
    @Operation(summary = "缓存蓝奏云Cookie",description = "缓存蓝奏云Cookie")
    public R cacheCookie(@RequestBody @Validated CookieVo cookieVo){
        return iResourceService.cacheCookie(cookieVo);
    }

    @Operation(summary = "添加图片",description ="添加资源仅支持.jpg.png 文件，调用本接口后需根据返回信息调用添加资源接口")
    @Parameter(name="file",description="上传文件",required=true)
    @Parameter(name="description",description="文件描述")
    @PostMapping("/resource/addImage")
    public R handleFileUploadImage(MultipartFile file,String description){
        return iResourceService.addResource(file,description,1);
    }
    @PostMapping("/resource/addvideo")
    @Operation(summary = "添加视频",description ="添加资源仅支持.mp4文件，调用本接口后需根据返回信息调用添加资源接口")
    @Parameter(name="file",description="上传文件",required=true)
    @Parameter(name="description",description="文件描述")
    public R handleFileUploadVideo(MultipartFile file,String description){
        return iResourceService.addResource(file,description,2);
    }
}
