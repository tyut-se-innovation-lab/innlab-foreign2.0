package tyut.selab.modular.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.common.constant.KeyConstants;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.common.utils.RedisUtils;
import tyut.selab.common.utils.StringUtils;
import tyut.selab.framework.domain.PageParam;
import tyut.selab.framework.domain.entity.ResourceEntity;
import tyut.selab.framework.mapper.ResourceMapper;
import tyut.selab.framework.web.SecurityUtils;
import tyut.selab.modular.domain.dto.AddHistoryDto;
import tyut.selab.modular.domain.dto.UpdateHistoryDto;
import tyut.selab.modular.domain.entity.HistoryEntity;
import tyut.selab.modular.domain.vo.HistoryVo;
import tyut.selab.modular.domain.vo.ImageVo;
import tyut.selab.modular.mapper.HistoryMapper;
import tyut.selab.modular.service.IHistoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HistoryServiceImpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-27 17:06
 * @Version: 1.0
 **/
@Service
public class HistoryServiceImpl implements IHistoryService {
    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public R getHistoryForeign(PageParam pageParam){
        Page<HistoryEntity> page = new Page<>(pageParam.getPageNum(),pageParam.getPageSize());
        QueryWrapper<HistoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state",true)
                .orderByDesc("history_time");
        Page<HistoryEntity> historyPage = historyMapper.selectPage(page,queryWrapper);
        List<HistoryVo> historyVoList = new ArrayList<>();
        historyPage.getRecords().forEach(historyEntity -> {
            HistoryVo historyVo = new HistoryVo(historyEntity);
            ResourceEntity resourceEntity = resourceMapper.selectById(historyEntity.getHeaderImage());
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
                historyVo.setHeaderImage(imageVo);
            }
            historyVoList.add(historyVo);
        });
        Page<HistoryVo> historyVoPage = new Page<>(historyPage.getCurrent(),historyPage.getSize(),historyPage.getTotal());
        historyVoPage.setRecords(historyVoList);
        return R.success(historyVoPage);
    }
    @Override
    public R addHistory(AddHistoryDto addHistoryDto){
        QueryWrapper<HistoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("history_title",addHistoryDto.getHistoryTitle());
        HistoryEntity historyEntity1 = historyMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotNull(historyEntity1)){
            return R.error("历史标题已存在！");
        }
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setHistoryTime(addHistoryDto.getHistoryTime());
        historyEntity.setHistoryTitle(addHistoryDto.getHistoryTitle());
        historyEntity.setHistoryContent(addHistoryDto.getHistoryContent());
        historyEntity.setState(false);
        historyEntity.setDelFlag(0);
        historyEntity.setCreateUser(SecurityUtils.getUserNickName());
        historyMapper.insert(historyEntity);
        return R.success("添加成功！");
    }
    @Override
    public R updateHistory(UpdateHistoryDto updateHistoryDto){
        HistoryEntity historyEntity1 = historyMapper.selectById(updateHistoryDto.getHistoryId());
        if (ObjectUtils.isNull(historyEntity1)){
            return R.error("历史结点不存在！");
        }
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setHistoryId(updateHistoryDto.getHistoryId());
        historyEntity.setHistoryTime(updateHistoryDto.getHistoryTime());
        historyEntity.setHistoryTitle(updateHistoryDto.getHistoryTitle());
        historyEntity.setHistoryContent(updateHistoryDto.getHistoryContent());
        historyEntity.setState(updateHistoryDto.getState());
        historyEntity.setUpdateUser(SecurityUtils.getUserNickName());
        historyMapper.updateById(historyEntity);
        return R.success("修改成功！");
    }
    @Override
    public R deleteHistoryById(Integer historyId){
        HistoryEntity historyEntity = historyMapper.selectById(historyId);
        if(ObjectUtils.isNull(historyEntity)){
            return R.error("历史结点不存在！");
        }
        historyMapper.deleteById(historyId);
        return R.success("删除成功！");
    }

    @Override
    public R getHistoryList(PageParam pageParam){
        Page<HistoryEntity> page = new Page<>(pageParam.getPageNum(),pageParam.getPageSize());
        QueryWrapper<HistoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("history_time");
        Page<HistoryEntity> historyPage = historyMapper.selectPage(page,queryWrapper);
        return R.success(historyPage);
    }
}
