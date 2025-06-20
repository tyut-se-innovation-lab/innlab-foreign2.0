package tyut.selab.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tyut.selab.common.constant.KeyConstants;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.*;
import tyut.selab.framework.domain.dto.param.ResourceParam;
import tyut.selab.framework.domain.entity.ResourceEntity;
import tyut.selab.framework.domain.vo.CookieVo;
import tyut.selab.framework.domain.vo.ResourceVo;
import tyut.selab.framework.mapper.ResourceMapper;
import tyut.selab.framework.service.IResourceService;
import tyut.selab.common.domain.Lz;
import tyut.selab.framework.web.SecurityUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ResourceServiceImpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-23 16:04
 * @Version: 1.0
 **/
@Slf4j
@Service
public class ResourceServiceImpl implements IResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 添加资源（添加资源为文件）
     *
     * @param file
     * @param type
     * @return
     */
    @Override
    public R addResource(MultipartFile file, String fileDescription,Integer type) {
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String imagePath = null;
        if (type==1 && (suffixName.equals(".jpg") || suffixName.equals(".png"))) {
            imagePath = "Image/";
        } else if (type==2 && suffixName.equals(".mp4")) {
            imagePath = "Video/";
        } else {
            return R.error("文件格式错误！");
        }
        //重新生成文件名
        Date date = new Date();
        String fileName1 = DateUtils.format(date) + RandomUtils.createCode(5);
        String fileName2 = fileName1 + suffixName;
        try {
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File("selab-resources/" + imagePath + fileName2)));
            out.write(file.getBytes());
            out.flush();
            out.close();
            ResourceEntity resourceEntity = new ResourceEntity();
            resourceEntity.setResourcePath(imagePath + fileName2);
            resourceEntity.setResourceName(fileName1);
            resourceEntity.setResourceType(type);
            resourceEntity.setDelFlag(0);
            resourceEntity.setResourceDescription(fileDescription);
            resourceEntity.setCreateUser(SecurityUtils.getUserNickName());
            resourceMapper.insert(resourceEntity);
            QueryWrapper<ResourceEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("resource_path", resourceEntity.getResourcePath());
            ResourceEntity resourceEntity1 =resourceMapper.selectOne(queryWrapper);
            R r = addResourceForCloud(resourceEntity.getResourceId());
            if (r.getCode()!=200){
                return r;
            }
            ResourceEntity resourceEntity2 = resourceMapper.selectById(resourceEntity1.getResourceId());
            ResourceVo resourceVo = new ResourceVo(resourceEntity2);
            return R.success(resourceVo);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("添加图片失败！");
        }
    }
    @Override
    public R addResource2(MultipartFile file, String fileDescription, Integer type) {
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        if (suffixName.equals(".png")||suffixName.equals(".jpg")||suffixName.equals(".mp4")){
            return R.error("上传图片/视频请使用对应接口！");
        }
        String imagePath = null;
        if (type==3) {
            imagePath = "Cache/";
        } else {
            return R.error("文件格式错误！");
        }
        //重新生成文件名
        Date date = new Date();
        String fileName1 = DateUtils.format(date) + RandomUtils.createCode(5);
//        String fileName2 = fileName1 + suffixName;
//        String fileName2 = fileName;
        try {
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File("selab-resources/" + imagePath + fileName1)));
            out.write(file.getBytes());
            out.flush();
            out.close();
            ResourceEntity resourceEntity = new ResourceEntity();
            resourceEntity.setResourcePath(imagePath + fileName1);
            resourceEntity.setResourceName(fileName1);
            resourceEntity.setResourceType(type);
            resourceEntity.setDelFlag(0);
            resourceEntity.setResourceDescription(fileDescription);
            resourceEntity.setCreateUser(SecurityUtils.getUserNickName());
            resourceMapper.insert(resourceEntity);
            QueryWrapper<ResourceEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("resource_path", resourceEntity.getResourcePath());
            ResourceEntity resourceEntity1 =resourceMapper.selectOne(queryWrapper);
            R r = addResourceForCloud2(resourceEntity.getResourceId());
            if (r.getCode()!=200){
                return r;
            }
            ResourceEntity resourceEntity2 = resourceMapper.selectById(resourceEntity1.getResourceId());
            ResourceVo resourceVo = new ResourceVo(resourceEntity2);
            return R.success(resourceVo);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("添加图片失败！");
        }
    }

    @Override
    public R addResourceForCloud(Integer resourceId) {
        ResourceEntity resourceEntity = resourceMapper.selectById(resourceId);
        if (StringUtils.isEmpty(resourceEntity.getResourcePath())) {
            return R.error("文件不存在！");
        }
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("selab-resources/" + resourceEntity.getResourcePath()));
            byte[] fileData = new byte[0];
            fileData = in.readAllBytes();
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File("selab-resources/Cache/" + resourceEntity.getResourcePath())));
            out.write(fileData);
            out.flush();
            out.close();
            if (redisUtils.hasKey("Resource_Cookie") && redisUtils.hasKey("Resource_Folder_Id")) {
                File file = new File("selab-resources/Cache/" + resourceEntity.getResourcePath());
                Lz lz = FigureBedUtils.addLz(file, redisUtils.getCacheObject("Resource_Cookie").toString(), redisUtils.getCacheObject("Resource_Folder_Id").toString());
                resourceEntity.setFId(lz.getFId());
                resourceEntity.setIsNewd(lz.getIsNewd());
                resourceEntity.setPwd(lz.getPwd());
                resourceMapper.updateById(resourceEntity);
            } else {
                return R.error("请上传蓝奏云Cookie");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success();
    }

    @Override
    public R addResourceForCloud2(Integer resourceId) {
        ResourceEntity resourceEntity = resourceMapper.selectById(resourceId);
        if (StringUtils.isEmpty(resourceEntity.getResourcePath())) {
            return R.error("文件不存在！");
        }
        try {
            if (redisUtils.hasKey("Resource_Cookie") && redisUtils.hasKey("Resource_Folder_Id")) {
                File file = new File("selab-resources/" + resourceEntity.getResourcePath());
                Lz lz = FigureBedUtils.addLz(file, redisUtils.getCacheObject("Resource_Cookie").toString(), redisUtils.getCacheObject("Resource_Folder_Id").toString());
                resourceEntity.setFId(lz.getFId());
                resourceEntity.setIsNewd(lz.getIsNewd());
                resourceEntity.setPwd(lz.getPwd());
                resourceMapper.updateById(resourceEntity);
            } else {
                return R.error("请上传蓝奏云Cookie");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success();
    }
    @Override
    public R cacheCookie(CookieVo cookieVo){
        redisUtils.setCacheObject("Resource_Cookie",cookieVo.getCookie());
        redisUtils.setCacheObject("Resource_Folder_Id",cookieVo.getFolderId());
        return R.success("上传成功！");
    }
    @Override
    public R getResourceLz(Integer resourceId){
        ResourceEntity resourceEntity = resourceMapper.selectById(resourceId);
        if (ObjectUtils.isNotNull(resourceEntity)&&ObjectUtils.isNotNull(resourceEntity.getFId())){
            Lz lz = new Lz(resourceEntity.getPwd(),resourceEntity.getFId(),resourceEntity.getIsNewd());
            return R.success(lz);
        }else {
            return R.error("文件蓝奏云资源不存在！");
        }
    }
    @Override
    public R getResourcelist(ResourceParam resourceParam){
        Page<ResourceEntity> page = new Page<>(resourceParam.getPageNum(),resourceParam.getPageSize());
        QueryWrapper<ResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtils.isNotNull(resourceParam.getResourceType()),"resource_type",resourceParam.getResourceType())
                .orderByDesc("resource_id");
        IPage<ResourceEntity> resourceEntityIPage = resourceMapper.selectPage(page, queryWrapper);
        resourceEntityIPage.getRecords().forEach( resourceEntity -> {
            String lzLinkUrl = (String) redisUtils.getCacheObject(KeyConstants.LZ_LINEURL_KEY+resourceEntity.getFId());
            if (StringUtils.isNotEmpty(lzLinkUrl)){
                resourceEntity.setResourceUrl(lzLinkUrl);
            }
        });
        return R.success(resourceEntityIPage);
    }

    @Async
    @Override
    public CompletableFuture<String> getResourceByLz(Lz lz){
        String lineUrl = null;
        String lzKey = KeyConstants.LZ_LINEURL_KEY + lz.getFId();
        if (redisUtils.hasKey(lzKey)) {
            lineUrl = (String) redisUtils.getCacheObject(lzKey);
            if (StringUtils.isNotBlank(lineUrl)){
                return CompletableFuture.completedFuture(lineUrl);
            }
        }

        return CompletableFuture.supplyAsync(() -> {
            String newLineUrl = FigureBedUtils.getLz(lz);
            if (StringUtils.isEmpty(newLineUrl)){
                return "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/f3be25102f2afcbceaadd64f56fafd5d6ab12cca4f192576264076015041801db06736e672fde394c5fc6a7d9558e197?pictype=scale&from=30013&version=3.3.3.3&fname=5e74a7832ff411f18ee66c4e542b2647.jpg&size=750";
            }
            redisUtils.setCacheObject(lzKey, newLineUrl, 30, TimeUnit.MINUTES);
            return newLineUrl;
        });
    }

    @Override
    public R getCacheImg1(){
        Map<String, Object> keys = redisUtils.scanValuesByPrefix("lz_lineurl:");
        Set<String> imgUrls = new HashSet<String>();
        for (Map.Entry<String, Object> key : keys.entrySet()) {
            String url = (String) key.getValue();
            String img = url.substring(url.length()-6);
            if (img.equals("jpg.it")||img.equals("png.it")){
             imgUrls.add(url);
            }
        }
        return R.success(imgUrls);
    }

    @Override
    public R deleteAllCache(){
        Long number = redisUtils.deleteKeysByPrefix("lz_lineurl:");
        return R.success("删除成功！",number);
    }
    @Override
    public R addWYImg(Set<String> urls){
        ResourceEntity resourceEntity = new ResourceEntity();
        for (String url :urls){
            resourceEntity.setResourceUrl(url+"&size=750");
            String name = url.substring(url.length()-19,url.length()-4);
            log.info(name);
            log.info(url);
            QueryWrapper<ResourceEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("resource_name",name);
            resourceMapper.update(resourceEntity,queryWrapper);
        }
        return R.success("上传成功！");
    }

    @Override
    public R getCacheImg2(){
        Map<String, Object> keys = redisUtils.scanValuesByPrefix("lz_lineurl:");
        Set<String> imgUrls = new HashSet<String>();
        for (Map.Entry<String, Object> key : keys.entrySet()) {
            String url = (String) key.getValue();
            String img = url.substring(url.length()-4);
            if (img.equals(".jpg")||img.equals(".png")){
                imgUrls.add(url);
            }
        }
        return R.success(imgUrls);
    }
    @Override
    public R getResourceById(Integer resourceId) throws ExecutionException, InterruptedException {
        ResourceEntity resourceEntity = resourceMapper.selectById(resourceId);
        if (ObjectUtils.isNull(resourceEntity)){
            return R.error("资源不存在！");
        }
        if (StringUtils.isNotBlank(resourceEntity.getResourceUrl())){
            return R.success(resourceEntity.getResourceUrl());
        }else {
            Lz lz = new Lz(resourceEntity.getPwd(),resourceEntity.getFId(),resourceEntity.getIsNewd());
            String url = getResourceByLz(lz).get();
            return R.success(url);
        }
    }

    @Override
    public R getResourceByLz2(Lz lz){
        String lineUrl = null;
        String lzKey = KeyConstants.LZ_LINEURL_KEY + lz.getFId();
        if (redisUtils.hasKey(lzKey)) {
            lineUrl = (String) redisUtils.getCacheObject(lzKey);
            if (StringUtils.isNotBlank(lineUrl)){
                return R.success(lineUrl);
            }
        }

        String newLineUrl = FigureBedUtils.getLz(lz);
        redisUtils.setCacheObject(lzKey, newLineUrl, 30, TimeUnit.MINUTES);
        return R.success(lineUrl);
    }
    public R getResourceBase64(Integer resourceId){
        ResourceEntity resourceEntity = resourceMapper.selectById(resourceId);
        if (ObjectUtils.isNotNull(resourceEntity)&&ObjectUtils.isNotNull(resourceEntity.getResourcePath())){
            String base64 = null;
            if (resourceEntity.getResourceType()==3){

                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("selab-resources/"+resourceEntity.getResourcePath()))) {
                    byte[] fileData = new byte[0];
                    fileData = in.readAllBytes();
                    // 处理文件内容，例如显示或保存文件内容
                    base64 = Base64.getEncoder().encodeToString(fileData);
                }catch (IOException e) {
                    // 处理异常
                    e.printStackTrace();
                }
            }else {
                return R.error("文件不支持Base64");
            }
            return R.success(base64);
        }else {
            return R.error("文件资源不存在！");
        }
    }

}
