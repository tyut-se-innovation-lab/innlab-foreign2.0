package tyut.selab.framework.service;


import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.dto.AddUserDto;
import tyut.selab.framework.domain.dto.LoginDto;
import tyut.selab.framework.domain.dto.VerifyRegisterDto;
import tyut.selab.framework.domain.dto.param.UserParam;

/**
 * @ClassName: IUserService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 17:39
 * @Version: 1.0
 **/
public interface IUserService {

    R login(LoginDto loginDto);

    R logoutUser();

    R addUser(AddUserDto addUserDto);

    R verifyRegister(VerifyRegisterDto verifyRegister);

    R getUserList(UserParam userParam);

    R getUserMsg(Integer userId);
}
