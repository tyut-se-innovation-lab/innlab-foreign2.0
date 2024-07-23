package tyut.selab.modular.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.common.constant.KeyConstants;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.EnumUtils;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.common.utils.RedisUtils;
import tyut.selab.common.utils.StringUtils;
import tyut.selab.framework.domain.entity.ResourceEntity;
import tyut.selab.framework.mapper.ResourceMapper;
import tyut.selab.modular.domain.dto.param.ActivityParam;
import tyut.selab.modular.domain.entity.ActivityEntity;
import tyut.selab.modular.domain.entity.SubTitleEntity;
import tyut.selab.modular.domain.model.SubTitleMo;
import tyut.selab.modular.domain.vo.*;
import tyut.selab.modular.mapper.ActivityMapper;
import tyut.selab.modular.mapper.SubTitleMapper;
import tyut.selab.modular.service.IActivityService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: ActivityServiceImpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-11 12:58
 * @Version: 1.0
 **/
@Service
public class ActivityServiceImpl implements IActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private SubTitleMapper subTitleMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public R shwoActivityTitle1(ActivityParam activityParam){
        Page<ActivityEntity> page = new Page<>(activityParam.getPageNum(), activityParam.getPageSize());
        QueryWrapper<ActivityEntity> itemEntityQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(activityParam.getDepartment())){
            itemEntityQueryWrapper.eq("activity_department",EnumUtils.getDepartmentIdByName(activityParam.getDepartment()));
        }
        itemEntityQueryWrapper.eq(ObjectUtils.isNotNull(activityParam.getActivityType()),"activity_type",activityParam.getActivityType())
                .eq("state",true)
                .orderByDesc("is_top")
                .orderByDesc("create_time");
        Page<ActivityEntity> activityEntityPage = activityMapper.selectPage(page,itemEntityQueryWrapper);
        List<ActivityTitleVo> activityTitleVos = new ArrayList<>();
        activityEntityPage.getRecords().forEach(activityEntity -> {
            ActivityTitleVo activityTitleVo = new ActivityTitleVo(activityEntity);
            activityTitleVos.add(activityTitleVo);
        });
//        itemTitleVos.remove(0);
        Page<ActivityTitleVo> activityTitleVoPage = new Page<>(activityEntityPage.getCurrent(),activityEntityPage.getSize(),activityEntityPage.getTotal());
        activityTitleVoPage.setRecords(activityTitleVos);
        return R.success(activityTitleVoPage);
    }
    @Override
    public R shwoActivityTitle2(ActivityParam activityParam){
        Page<ActivityEntity> page = new Page<>(activityParam.getPageNum(), activityParam.getPageSize());
        QueryWrapper<ActivityEntity> itemEntityQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(activityParam.getDepartment())){
            itemEntityQueryWrapper.eq("activity_department",EnumUtils.getDepartmentIdByName(activityParam.getDepartment()));
        }
        itemEntityQueryWrapper.eq(ObjectUtils.isNotNull(activityParam.getActivityType()),"activity_type",activityParam.getActivityType())
                .eq("state",true)
                .orderByDesc("is_top")
                .orderByDesc("create_time");
        Page<ActivityEntity> activityEntityPage = activityMapper.selectPage(page,itemEntityQueryWrapper);
        List<ActivityTitleVo> activityTitleVos = new ArrayList<>();
        activityEntityPage.getRecords().forEach(activityEntity -> {
            ActivityTitleVo activityTitleVo = new ActivityTitleVo(activityEntity);
            ResourceEntity resourceEntity = resourceMapper.selectById(activityEntity.getHeaderImage());
            if (ObjectUtils.isNotNull(resourceEntity)){
                ImageVo imageVo = new ImageVo();
                imageVo.setIsNewd(resourceEntity.getIsNewd());
                imageVo.setPwd(resourceEntity.getPwd());
                imageVo.setFId(resourceEntity.getFId());
                String lzLinkUrl = (String) redisUtils.getCacheObject(KeyConstants.LZ_LINEURL_KEY+imageVo.getFId());
                if (StringUtils.isNotEmpty(lzLinkUrl)){
                    imageVo.setUrl(lzLinkUrl);
                }else {
                    imageVo.setUrl(resourceEntity.getResourceUrl());
                }
                activityTitleVo.setHeaderImage(imageVo);
            }
            activityTitleVos.add(activityTitleVo);
        });
//        itemTitleVos.remove(0);
        Page<ActivityTitleVo> activityTitleVoPage = new Page<>(activityEntityPage.getCurrent(),activityEntityPage.getSize(),activityEntityPage.getTotal());
        activityTitleVoPage.setRecords(activityTitleVos);
        return R.success(activityTitleVoPage);
    }

    @Override
    public R getActivityMsg(Integer activityId) {
       ActivityEntity activityEntity = activityMapper.selectById(activityId);
        if (ObjectUtils.isNull(activityEntity)){
            return R.error("文章不存在！");
        }
        ActivityMsgVo activityMsgVo  = new ActivityMsgVo(activityEntity);
        ResourceEntity resourceEntity1 = resourceMapper.selectById(activityEntity.getHeaderImage());
        if (ObjectUtils.isNotNull(resourceEntity1)){
            ImageVo imageVo = new ImageVo();
            imageVo.setIsNewd(resourceEntity1.getIsNewd());
            imageVo.setPwd(resourceEntity1.getPwd());
            imageVo.setFId(resourceEntity1.getFId());
            String lzLinkUrl = (String) redisUtils.getCacheObject(KeyConstants.LZ_LINEURL_KEY+imageVo.getFId());
            if (StringUtils.isNotEmpty(lzLinkUrl)){
                imageVo.setUrl(lzLinkUrl);
            }else {
                imageVo.setUrl(resourceEntity1.getResourceUrl());
            }
            activityMsgVo.setHeaderImage(imageVo);
        }
        QueryWrapper<SubTitleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activity_id",activityId).orderByAsc("subtitle_sort");
        List<SubTitleEntity> subTitleEntityList = subTitleMapper.selectList(queryWrapper);
        List<SubTitleMo> subTitleMoList = new ArrayList<>();
        for (SubTitleEntity subTitleEntity :subTitleEntityList){
            SubTitleMo subTitleMo = new SubTitleMo();
            subTitleMo.setSubtitleName(subTitleEntity.getSubtitleName());
            subTitleMo.setSubtitleType(subTitleEntity.getSubtitleType());
            if (subTitleEntity.getSubtitleType()==0){
                subTitleMo.setSubtitleContent(subTitleEntity.getSubtitleContent());
            }else {
                String arr[] = subTitleEntity.getSubtitleContent().split("\\.");
                List<ImageVo> resource = new ArrayList<>();
                List<ResourceEntity> resourceEntityList = resourceMapper.selectBatchIds(Arrays.asList(arr));
                for (ResourceEntity resourceEntity:resourceEntityList){
                    ImageVo imageVo = new ImageVo();
                    imageVo.setIsNewd(resourceEntity.getIsNewd());
                    imageVo.setPwd(resourceEntity.getPwd());
                    imageVo.setFId(resourceEntity.getFId());
                    String lzLinkUrl = (String) redisUtils.getCacheObject(KeyConstants.LZ_LINEURL_KEY+imageVo.getFId());
                    if (StringUtils.isNotEmpty(lzLinkUrl)){
                        imageVo.setUrl(lzLinkUrl);
                    }else {
                        imageVo.setUrl(resourceEntity.getResourceUrl());
                    }
                    resource.add(imageVo);
                }
                subTitleMo.setResource(resource);
            }
            subTitleMoList.add(subTitleMo);
        }
        activityMsgVo.setActivityContent(subTitleMoList);
            return R.success(activityMsgVo);
    }

    public R getActivityList(ActivityParam activityParam){
        Page<ActivityEntity> page = new Page<>(activityParam.getPageNum(), activityParam.getPageSize());
        QueryWrapper<ActivityEntity> itemEntityQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(activityParam.getDepartment())){
            itemEntityQueryWrapper.eq("activity_department",EnumUtils.getDepartmentIdByName(activityParam.getDepartment()));
        }
        itemEntityQueryWrapper.eq(ObjectUtils.isNotNull(activityParam.getActivityType()),"activity_type",activityParam.getActivityType())
                .orderByDesc("is_top")
                .orderByDesc("create_time");
        Page<ActivityEntity> activityEntityPage = activityMapper.selectPage(page,itemEntityQueryWrapper);
        List<ActivityTitleVo> activityTitleVos = new ArrayList<>();
        activityEntityPage.getRecords().forEach(activityEntity -> {
            ActivityTitleVo activityTitleVo = new ActivityTitleVo(activityEntity);
            ResourceEntity resourceEntity = resourceMapper.selectById(activityEntity.getHeaderImage());
            if (ObjectUtils.isNotNull(resourceEntity)){
                ImageVo imageVo = new ImageVo();
                imageVo.setIsNewd(resourceEntity.getIsNewd());
                imageVo.setPwd(resourceEntity.getPwd());
                imageVo.setFId(resourceEntity.getFId());
                String lzLinkUrl = (String) redisUtils.getCacheObject(KeyConstants.LZ_LINEURL_KEY+imageVo.getFId());
                if (StringUtils.isNotEmpty(lzLinkUrl)){
                    imageVo.setUrl(lzLinkUrl);
                }else {
                    imageVo.setUrl(resourceEntity.getResourceUrl());
                }
                activityTitleVo.setHeaderImage(imageVo);
            }
            activityTitleVos.add(activityTitleVo);
        });
        Page<ActivityTitleVo> activityTitleVoPage = new Page<>(activityEntityPage.getCurrent(),activityEntityPage.getSize(),activityEntityPage.getTotal());
        activityTitleVoPage.setRecords(activityTitleVos);
        return R.success(activityTitleVoPage);
    }
}

