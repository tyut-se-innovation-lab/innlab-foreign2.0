package tyut.selab.modular.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.EnumUtils;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.framework.domain.entity.ResourceEntity;
import tyut.selab.framework.mapper.ResourceMapper;
import tyut.selab.modular.domain.dto.param.ItemParam;
import tyut.selab.modular.domain.entity.ItemEntity;
import tyut.selab.modular.domain.vo.ImageVo;
import tyut.selab.modular.domain.vo.ItemMsgVo;
import tyut.selab.modular.domain.vo.ItemTitleVo;
import tyut.selab.modular.mapper.ItemMapper;
import tyut.selab.modular.service.IItemService;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
    @Override
    public R showItemsTitle1(ItemParam itemParam) {
        Page<ItemEntity> page = new Page<>(itemParam.getPageNum(), itemParam.getPageSize());
        QueryWrapper<ItemEntity> itemEntityQueryWrapper = new QueryWrapper<>();
        System.out.println(EnumUtils.getDepartmentIdByName(itemParam.getDepartment()));
        itemEntityQueryWrapper.eq("department_id", EnumUtils.getDepartmentIdByName(itemParam.getDepartment()))
                .eq("state",true)
                .orderByDesc("is_top");
        Page<ItemEntity> itemEntityPage = itemMapper.selectPage(page,itemEntityQueryWrapper);
        List<ItemTitleVo> itemTitleVos = new ArrayList<>();
        itemEntityPage.getRecords().forEach(itemEntity -> {
            ItemTitleVo itemTitleVo = new ItemTitleVo();
            itemTitleVo.setItemId(itemEntity.getItemId());
            itemTitleVo.setItemTitle(itemEntity.getItemTitle());
            itemTitleVos.add(itemTitleVo);
        });
//        itemTitleVos.remove(0);
        Page<ItemTitleVo> itemTitleVoPage = new Page<>(itemEntityPage.getCurrent(),itemEntityPage.getSize(),itemEntityPage.getTotal());
        itemTitleVoPage.setRecords(itemTitleVos);
        return R.success(itemTitleVoPage);
    }
    @Override
    public R showItemsTitle2(ItemParam itemParam) {
        Page<ItemEntity> page = new Page<>(itemParam.getPageNum(), itemParam.getPageSize());
        QueryWrapper<ItemEntity> itemEntityQueryWrapper = new QueryWrapper<>();
        itemEntityQueryWrapper.eq("department_id", EnumUtils.getDepartmentIdByName(itemParam.getDepartment()))
                .eq("state",true)
                .orderByDesc("is_top");
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
                itemTitleVo.setHeaderImage(imageVo);
            }
            itemTitleVos.add(itemTitleVo);
        });
//        itemTitleVos.remove(0);
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
        ItemMsgVo itemMsgVo = new ItemMsgVo(itemEntity);
//        ImageVo imageVo = new ImageVo();
//        imageVo.setIsNewd(resourceEntity.getIsNewd());
//        imageVo.setPwd(resourceEntity.getPwd());
//        imageVo.setFId(resourceEntity.getFId());
        String base64 = null;
        if (ObjectUtils.isNotNull(itemEntity.getContentMarkdown())){
            itemMsgVo.setMarkdownBase64(itemEntity.getContentMarkdown());
            return R.success(itemMsgVo);
        }
        return R.error("文章已删除！");
    }
}
