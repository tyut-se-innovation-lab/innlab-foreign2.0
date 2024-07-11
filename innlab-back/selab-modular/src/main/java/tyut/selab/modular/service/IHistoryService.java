package tyut.selab.modular.service;

import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.PageParam;

/**
 * @ClassName: HistoryService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-27 17:07
 * @Version: 1.0
 **/
public interface IHistoryService {
    R getHistoryForeign(PageParam pageParam);
}
