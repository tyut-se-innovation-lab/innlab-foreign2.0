package tyut.selab.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ResourceServiceImpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-23 16:04
 * @Version: 1.0
 **/
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
                    new FileOutputStream(new File("selab-resources/Cache/" + resourceEntity.getResourcePath() + ".it")));
            out.write(fileData);
            out.flush();
            out.close();
            if (redisUtils.hasKey("Resource_Cookie") && redisUtils.hasKey("Resource_Folder_Id")) {
                File file = new File("selab-resources/Cache/" + resourceEntity.getResourcePath() + ".it");
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
        queryWrapper.eq(ObjectUtils.isNotNull(resourceParam.getResourceType()),"resource_type",resourceParam.getResourceType());
        IPage<ResourceEntity> resourceEntityIPage = resourceMapper.selectPage(page, queryWrapper);
        return R.success(resourceEntityIPage);
    }

    @Async
    @Override
    public CompletableFuture<String> getResourceByLz(Lz lz){
        String lineUrl = null;
        String lzKey = KeyConstants.LZ_LINEURL_KEY + lz.getFId();
        if (redisUtils.hasKey(lzKey)) {
            lineUrl = (String) redisUtils.getCacheObject(lzKey);
            return CompletableFuture.completedFuture(lineUrl);
        }

        return CompletableFuture.supplyAsync(() -> {
            String newLineUrl = FigureBedUtils.getLz(lz);
            redisUtils.setCacheObject(lzKey, newLineUrl, 10, TimeUnit.MINUTES);
            return newLineUrl;
        });
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
