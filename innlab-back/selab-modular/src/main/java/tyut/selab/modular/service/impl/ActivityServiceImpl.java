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
import tyut.selab.framework.web.SecurityUtils;
import tyut.selab.modular.domain.dto.AddActivityDto;
import tyut.selab.modular.domain.dto.AddSubTitleDto;
import tyut.selab.modular.domain.dto.UpdateActivityDto;
import tyut.selab.modular.domain.dto.UpdateSubTitleDto;
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
        if (ObjectUtils.isNull(activityEntity)||!activityEntity.getState()){
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

    @Override
    public R getActivityList(ActivityParam activityParam){
        Page<ActivityEntity> page = new Page<>(activityParam.getPageNum(), activityParam.getPageSize());
        QueryWrapper<ActivityEntity> itemEntityQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(activityParam.getDepartment())){
            itemEntityQueryWrapper.eq("activity_department",EnumUtils.getDepartmentIdByName(activityParam.getDepartment()));
        }
        itemEntityQueryWrapper.eq(ObjectUtils.isNotNull(activityParam.getActivityType()),"activity_type",activityParam.getActivityType())
                .orderByDesc("is_top")
                .orderByAsc("state")
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
                activityTitleVo.setHeaderImageId(resourceEntity.getResourceId());
                activityTitleVo.setHeaderImage(imageVo);
            }
            activityTitleVos.add(activityTitleVo);
        });
        Page<ActivityTitleVo> activityTitleVoPage = new Page<>(activityEntityPage.getCurrent(),activityEntityPage.getSize(),activityEntityPage.getTotal());
        activityTitleVoPage.setRecords(activityTitleVos);
        return R.success(activityTitleVoPage);
    }

    @Override
    public R addActivity(AddActivityDto addActivityDto){
        ResourceEntity resourceEntity = resourceMapper.selectById(addActivityDto.getHeaderImage());
        if (ObjectUtils.isNull(resourceEntity)){
            return R.error("标题图片不存在，请先上传图片！");
        }
        ActivityEntity activityEntity = new ActivityEntity();
        if (StringUtils.isNotEmpty(addActivityDto.getActivityDepartment())){
            activityEntity.setActivityDepartment(EnumUtils.getDepartmentIdByName(addActivityDto.getActivityDepartment()));
        }
        activityEntity.setHeaderImage(addActivityDto.getHeaderImage());
        activityEntity.setActivityTitle(addActivityDto.getActivityTitle());
        activityEntity.setActivityType(addActivityDto.getActivityType());
        activityEntity.setActivityIntroduction(addActivityDto.getActivityIntroduction());
        activityEntity.setCreateUser(SecurityUtils.getUserNickName());
        activityEntity.setState(false);
        activityEntity.setDelFlag(0);
        activityMapper.insert(activityEntity);
        return R.success("添加成功！");
    }

    @Override
    public R deleteActivity(Integer activityId){
        ActivityEntity activityEntity = activityMapper.selectById(activityId);
        if (ObjectUtils.isNull(activityEntity)){
            return R.error("该机构动态不存在！");
        }
        activityMapper.deleteById(activityId);
        return R.success("删除成功！");
    }

    @Override
    public R updateActivity(UpdateActivityDto updateActivityDto){
        ActivityEntity activityEntity1 = activityMapper.selectById(updateActivityDto.getActivityId());
        if (ObjectUtils.isNull(activityEntity1)){
            return R.error("该机构动态不存在！");
        }
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityId(updateActivityDto.getActivityId());
        if (StringUtils.isNotEmpty(updateActivityDto.getActivityDepartment())){
            activityEntity.setActivityDepartment(EnumUtils.getDepartmentIdByName(updateActivityDto.getActivityDepartment()));
        }
        activityEntity.setActivityIntroduction(updateActivityDto.getActivityIntroduction());
        activityEntity.setActivityTitle(updateActivityDto.getActivityTitle());
        activityEntity.setHeaderImage(updateActivityDto.getHeaderImage());
        activityEntity.setState(updateActivityDto.getState());
        activityEntity.setIsTop(updateActivityDto.getIsTop());
        activityEntity.setUpdateUser(SecurityUtils.getUserNickName());
        activityEntity.setActivityType(updateActivityDto.getActivityType());
        activityMapper.updateById(activityEntity);
        return R.success("修改成功！");
    }
    @Override
    public R addSubTitle(AddSubTitleDto addSubTitleDto){
        ActivityEntity activityEntity1 = activityMapper.selectById(addSubTitleDto.getActivityId());
        if (ObjectUtils.isNull(activityEntity1)){
            return R.error("活动不存在！");
        }
        SubTitleEntity subTitleEntity = new SubTitleEntity();
        subTitleEntity.setActivityId(addSubTitleDto.getActivityId());
        subTitleEntity.setSubtitleType(addSubTitleDto.getSubtitleType());
        subTitleEntity.setSubtitleSort(addSubTitleDto.getSubtitleSort());
        subTitleEntity.setDelFlag(0);
        subTitleMapper.insert(subTitleEntity);
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityId(addSubTitleDto.getActivityId());
        activityEntity.setUpdateUser(SecurityUtils.getUserNickName());
        activityMapper.updateById(activityEntity);
        return R.success("添加成功！",subTitleEntity);
    }
    @Override
    public R updateSubTitle(UpdateSubTitleDto updateSubTitleDto){
        SubTitleEntity subTitleEntity1 = subTitleMapper.selectById(updateSubTitleDto.getSubtitleId());
        if (ObjectUtils.isNull(subTitleEntity1)){
            return R.error("小标题不存在！");
        }
        SubTitleEntity subTitleEntity = new SubTitleEntity();
        subTitleEntity.setSubtitleId(updateSubTitleDto.getSubtitleId());
        subTitleEntity.setSubtitleName(updateSubTitleDto.getSubtitleName());
        subTitleEntity.setSubtitleSort(updateSubTitleDto.getSubtitleSort());
        subTitleEntity.setSubtitleContent(updateSubTitleDto.getSubtitleContent());
        subTitleMapper.updateById(subTitleEntity);
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityId(subTitleEntity1.getActivityId());
        activityEntity.setUpdateUser(SecurityUtils.getUserNickName());
        activityMapper.updateById(activityEntity);
        return R.success("修改成功！",subTitleEntity);
    }
    @Override
    public R deleteSubTitleById(Integer subTitleId){
        SubTitleEntity subTitleEntity1 = subTitleMapper.selectById(subTitleId);
        if (ObjectUtils.isNull(subTitleEntity1)){
            return R.error("小标题不存在！");
        }
        subTitleMapper.deleteById(subTitleId);
        return R.success("删除成功！");
    }
    @Override
    public R getActivityMsg1(Integer activityId) {
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
            subTitleMo.setSubtitleId(subTitleEntity.getSubtitleId());
            subTitleMo.setSubtitleName(subTitleEntity.getSubtitleName());
            subTitleMo.setSubtitleType(subTitleEntity.getSubtitleType());
            subTitleMo.setSubtitleSort(subTitleEntity.getSubtitleSort());
            subTitleMo.setSubtitleContent(subTitleEntity.getSubtitleContent());
            if (subTitleEntity.getSubtitleType()!=0){
                subTitleMo.setSubtitleContent(subTitleEntity.getSubtitleContent());
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
}

