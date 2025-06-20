package tyut.selab.framework.service;


import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.dto.*;
import tyut.selab.framework.domain.dto.param.UserParam;

import java.util.concurrent.ExecutionException;

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

    R getUserMsgById(Integer userId) throws ExecutionException, InterruptedException;

    R updateUser(UpdateUserDto updateUserDto);

    R updateUserPassword(UpdateUserPasswordDto updateUserPasswordDto);

    R resetUserPassword(Integer userId);

    R delectUserById(Integer userId);
}
