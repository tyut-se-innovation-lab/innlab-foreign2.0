package tyut.selab.modular.service;

import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.PageParam;
import tyut.selab.modular.domain.dto.AddHistoryDto;
import tyut.selab.modular.domain.dto.UpdateHistoryDto;

/**
 * @ClassName: HistoryService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-27 17:07
 * @Version: 1.0
 **/
public interface IHistoryService {
    R getHistoryForeign(PageParam pageParam);

    R addHistory(AddHistoryDto addHistoryDto);

    R updateHistory(UpdateHistoryDto updateHistoryDto);

    R deleteHistoryById(Integer historyId);

    R getHistoryList(PageParam pageParam);
}
