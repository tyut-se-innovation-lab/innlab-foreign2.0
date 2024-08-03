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
import tyut.selab.modular.domain.dto.AddItemDto;
import tyut.selab.modular.domain.dto.UpdateItemDto;
import tyut.selab.modular.domain.dto.param.ItemParam;
import tyut.selab.modular.domain.entity.ItemEntity;
import tyut.selab.modular.domain.vo.ImageVo;
import tyut.selab.modular.domain.vo.ItemMsgVo;
import tyut.selab.modular.domain.vo.ItemTitleVo;
import tyut.selab.modular.mapper.ItemMapper;
import tyut.selab.modular.service.IItemService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ItemServiceImpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-24 16:44
 * @Version: 1.0
 **/
@Service
public class ItemServiceImpl implements IItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ResourceMapper resourceMapper;


    @Autowired
    private RedisUtils redisUtils;
    @Override
    public R showItemsTitle1(ItemParam itemParam) {
        Page<ItemEntity> page = new Page<>(itemParam.getPageNum(), itemParam.getPageSize());
        QueryWrapper<ItemEntity> itemEntityQueryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isNotNull(itemParam.getDepartment())){
            itemEntityQueryWrapper.eq("department_id", EnumUtils.getDepartmentIdByName(itemParam.getDepartment()));
        }
        itemEntityQueryWrapper.eq("state",true)
                .orderByDesc("is_top")
                .orderByDesc("item_id");
        Page<ItemEntity> itemEntityPage = itemMapper.selectPage(page,itemEntityQueryWrapper);
        List<ItemTitleVo> itemTitleVos = new ArrayList<>();
        itemEntityPage.getRecords().forEach(itemEntity -> {
            ItemTitleVo itemTitleVo = new ItemTitleVo();
            itemTitleVo.setItemId(itemEntity.getItemId());
            itemTitleVo.setItemTitle(itemEntity.getItemTitle());
            itemTitleVos.add(itemTitleVo);
        });
        Page<ItemTitleVo> itemTitleVoPage = new Page<>(itemEntityPage.getCurrent(),itemEntityPage.getSize(),itemEntityPage.getTotal());
        itemTitleVoPage.setRecords(itemTitleVos);
        return R.success(itemTitleVoPage);
    }
    @Override
    public R showItemsTitle2(ItemParam itemParam) {
        Page<ItemEntity> page = new Page<>(itemParam.getPageNum(), itemParam.getPageSize());
        QueryWrapper<ItemEntity> itemEntityQueryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isNotNull(itemParam.getDepartment())){
            itemEntityQueryWrapper.eq("department_id", EnumUtils.getDepartmentIdByName(itemParam.getDepartment()));
        }
        itemEntityQueryWrapper.eq("state",true)
                .orderByDesc("is_top")
                .orderByDesc("item_id");
        Page<ItemEntity> itemEntityPage = itemMapper.selectPage(page,itemEntityQueryWrapper);
        List<ItemTitleVo> itemTitleVos = new ArrayList<>();
        itemEntityPage.getRecords().forEach(itemEntity -> {
            ItemTitleVo itemTitleVo = new ItemTitleVo();
            itemTitleVo.setItemId(itemEntity.getItemId());
            itemTitleVo.setItemTitle(itemEntity.getItemTitle());
            itemTitleVo.setItemIntroduction(itemEntity.getItemIntroduction());
            ResourceEntity resourceEntity = resourceMapper.selectById(itemEntity.getHeaderImage());
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
                itemTitleVo.setHeaderImage(imageVo);
            }
            itemTitleVos.add(itemTitleVo);
        });
        Page<ItemTitleVo> itemTitleVoPage = new Page<>(itemEntityPage.getCurrent(),itemEntityPage.getSize(),itemEntityPage.getTotal());
        itemTitleVoPage.setRecords(itemTitleVos);
        return R.success(itemTitleVoPage);
    }
    @Override
    public R getItemMsg(Integer itemId) {
        ItemEntity itemEntity = itemMapper.selectById(itemId);
        if (ObjectUtils.isNull(itemEntity)){
            return R.error("文章不存在！");
        }
        if (!itemEntity.getState()){
            return R.error("文章不存在！");
        }
        ItemMsgVo itemMsgVo = new ItemMsgVo(itemEntity);
        ResourceEntity resourceEntity = resourceMapper.selectById(itemEntity.getHeaderImage());
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
        itemMsgVo.setHeaderImage(imageVo);
        itemMsgVo.setItemDepartment(EnumUtils.getDepartmentNameById(String.valueOf(itemEntity.getDepartmentId())));
        if (ObjectUtils.isNotNull(itemEntity.getContentMarkdown())){
            itemMsgVo.setMarkdownBase64(itemEntity.getContentMarkdown());
            return R.success(itemMsgVo);
        }
        return R.error("文章已删除！");
    }
    @Override
    public R getItemMsg1(Integer itemId) {
        ItemEntity itemEntity = itemMapper.selectById(itemId);
        if (ObjectUtils.isNull(itemEntity)){
            return R.error("文章不存在！");
        }
        ItemMsgVo itemMsgVo = new ItemMsgVo(itemEntity);
        ResourceEntity resourceEntity = resourceMapper.selectById(itemEntity.getHeaderImage());
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
        itemMsgVo.setHeaderImage(imageVo);
        itemMsgVo.setItemDepartment(EnumUtils.getDepartmentNameById(String.valueOf(itemEntity.getDepartmentId())));
        if (ObjectUtils.isNotNull(itemEntity.getContentMarkdown())){
            itemMsgVo.setMarkdownBase64(itemEntity.getContentMarkdown());
            return R.success(itemMsgVo);
        }
        return R.error("文章已删除！");
    }

    @Override
    public R addItem(AddItemDto addItemDto){
        ResourceEntity resourceEntity = resourceMapper.selectById(addItemDto.getHeaderImage());
        if (ObjectUtils.isNull(resourceEntity)){
            return R.error("标题图片不存在，请先上传图片！");
        }
        QueryWrapper<ItemEntity> itemEntityQueryWrapper = new QueryWrapper<>();
        itemEntityQueryWrapper.eq("item_title",addItemDto.getItemTitle());
        ItemEntity itemEntity1 = itemMapper.selectOne(itemEntityQueryWrapper);
        if (ObjectUtils.isNotNull(itemEntity1)){
            return R.error("项目标题已存在！");
        }
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemIntroduction(addItemDto.getItemIntroduction());
        itemEntity.setItemTitle(addItemDto.getItemTitle());
        itemEntity.setDepartmentId(EnumUtils.getDepartmentIdByName(addItemDto.getDepartment()));
        itemEntity.setHeaderImage(addItemDto.getHeaderImage());
        itemEntity.setCreateUser(SecurityUtils.getUserNickName());
        itemEntity.setState(false);
        itemEntity.setDelFlag(0);
        itemMapper.insert(itemEntity);
        return R.success("添加成功！");
    }
    @Override
    public R getItemList(ItemParam itemParam) {
        Page<ItemEntity> page = new Page<>(itemParam.getPageNum(), itemParam.getPageSize());
        QueryWrapper<ItemEntity> itemEntityQueryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isNotNull(itemParam.getDepartment())){
            itemEntityQueryWrapper.eq("department_id", EnumUtils.getDepartmentIdByName(itemParam.getDepartment()));
        }
        itemEntityQueryWrapper
                .orderByDesc("is_top")
                .orderByDesc("item_id");
        Page<ItemEntity> itemEntityPage = itemMapper.selectPage(page,itemEntityQueryWrapper);
        List<ItemTitleVo> itemTitleVos = new ArrayList<>();
        itemEntityPage.getRecords().forEach(itemEntity -> {
            ItemTitleVo itemTitleVo = new ItemTitleVo();
            itemTitleVo.setItemId(itemEntity.getItemId());
            itemTitleVo.setItemTitle(itemEntity.getItemTitle());
            itemTitleVo.setItemIntroduction(itemEntity.getItemIntroduction());
            itemTitleVo.setIsTop(itemEntity.getIsTop());
            itemTitleVo.setDepartment(EnumUtils.getDepartmentNameById(String.valueOf(itemEntity.getDepartmentId())));
            itemTitleVo.setState(itemEntity.getState());
            ResourceEntity resourceEntity = resourceMapper.selectById(itemEntity.getHeaderImage());
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
                itemTitleVo.setHeaderImage(imageVo);
            }
            itemTitleVos.add(itemTitleVo);
        });
        Page<ItemTitleVo> itemTitleVoPage = new Page<>(itemEntityPage.getCurrent(),itemEntityPage.getSize(),itemEntityPage.getTotal());
        itemTitleVoPage.setRecords(itemTitleVos);
        return R.success(itemTitleVoPage);
    }

    @Override
    public R updateItem(UpdateItemDto updateItemDto){
        ItemEntity itemEntity1 = itemMapper.selectById(updateItemDto.getItemId());
        if (ObjectUtils.isNull(itemEntity1)){
            return R.error("项目不存在！");
        }
        ItemEntity itemEntity = new ItemEntity();
        if(ObjectUtils.isNotNull(updateItemDto.getDepartment())){
            itemEntity.setDepartmentId(EnumUtils.getDepartmentIdByName(updateItemDto.getDepartment()));
        }
        itemEntity.setItemId(updateItemDto.getItemId());
        itemEntity.setState(updateItemDto.getState());
        itemEntity.setHeaderImage(updateItemDto.getHeaderImage());
        itemEntity.setContentMarkdown(updateItemDto.getContentMarkdown());
        itemEntity.setItemIntroduction(updateItemDto.getItemIntroduction());
        itemEntity.setIsTop(updateItemDto.getIsTop());
        itemEntity.setItemTitle(updateItemDto.getItemTitle());
        itemEntity.setGithubUrl(updateItemDto.getGithubUrl());
        itemEntity.setUpdateUser(SecurityUtils.getUserNickName());
        itemMapper.updateById(itemEntity);
        return R.success("修改成功！");
    }
    @Override
    public R deleteItemById(Integer itemId){
        ItemEntity itemEntity = itemMapper.selectById(itemId);
        if (ObjectUtils.isNull(itemEntity)){
            return R.error("项目不存在！");
        }
        itemMapper.deleteById(itemId);
        return R.success("删除成功！");
    }
}
