package tyut.selab.modular.service;

import tyut.selab.common.domain.R;
import tyut.selab.modular.domain.dto.param.PersonnelParam;

/**
 * @ClassName: PersonnelService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-27 17:07
 * @Version: 1.0
 **/
public interface IPersonnelService {
    R getPersonnelForeign(PersonnelParam personnelParam);
}
