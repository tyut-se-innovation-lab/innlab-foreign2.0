package tyut.selab.modular.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.PageParam;
import tyut.selab.modular.domain.entity.HistoryEntity;
import tyut.selab.modular.domain.vo.HistoryVo;
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
    @Override
    public R getHistoryForeign(PageParam pageParam){
        Page<HistoryEntity> page = new Page<>(pageParam.getPageNum(),pageParam.getPageSize());
        QueryWrapper<HistoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state",true)
                .orderByDesc("history_time");
        Page<HistoryEntity> historyPage = historyMapper.selectPage(page,queryWrapper);
        List<HistoryVo> historyVoList = new ArrayList<>();
        historyPage.getRecords().forEach(historyEntity -> {
            HistoryVo historyVo = new HistoryVo();
            historyVo.setHistoryContent(historyEntity.getHistoryContent());
            historyVo.setHistoryTitle(historyEntity.getHistoryTitle());
            historyVo.setHistoryTime(historyEntity.getHistoryTime());
            historyVoList.add(historyVo);
        });
        Page<HistoryVo> historyVoPage = new Page<>(historyPage.getCurrent(),historyPage.getSize(),historyPage.getTotal());
        historyVoPage.setRecords(historyVoList);
        return R.success(historyVoPage);
    }
}
