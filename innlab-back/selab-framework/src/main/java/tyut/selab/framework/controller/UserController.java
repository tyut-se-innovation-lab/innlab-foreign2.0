package tyut.selab.framework.controller;

import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tyut.selab.common.annotation.AccessLogAnnotation;
import tyut.selab.common.annotation.LoginLogAnnotation;
import tyut.selab.common.annotation.SysLogAnnotation;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.http.AddressUtil;
import tyut.selab.common.utils.http.IpUtil;
import tyut.selab.framework.domain.dto.AddUserDto;
import tyut.selab.framework.domain.dto.LoginDto;
import tyut.selab.framework.domain.dto.VerifyRegisterDto;
import tyut.selab.framework.domain.dto.param.UserParam;
import tyut.selab.framework.service.IUserService;
import tyut.selab.framework.web.SecurityUtils;
import tyut.selab.framework.web.service.LoginService;

/**
 * @ClassName: UserController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-20 10:58
 * @Version: 1.0
 **/
@RestController
@Tag(name = "用户")
@RequestMapping("/background")
@Slf4j
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @Operation(summary = "用户登陆")
    @LoginLogAnnotation()
    public R login(@RequestBody @Validated LoginDto loginDto) {
        R verifyr = loginService.mayLogin(loginDto.getAccount());
        if (verifyr.getCode()!=200){
            return verifyr;
        }
        R r = iUserService.login(loginDto);
        return r;
    }

    @PostMapping("/logout")
    @Operation(summary = "用户登出")
    public R logout() {
        R r = iUserService.logoutUser();
        return r;
    }
    @PostMapping("/register")
    @AccessLogAnnotation(method = "用户注册")
    @Operation(summary = "用户注册")
    public R register(@RequestBody @Validated AddUserDto addUserDto) {
        R verifyr = loginService.mayRegister(addUserDto.getUserAccount(), addUserDto.getUserEmail(),addUserDto.getVerificationCode());
        if (verifyr.getCode()!=200){
            return verifyr;
        }
        R r = iUserService.addUser(addUserDto);
        return r;
    }

    @PostMapping("/register/verify")
    @Operation(summary = "用户注册验证")
    @AccessLogAnnotation(method = "用户注册验证")
    public R verifyRegister(@RequestBody @Validated VerifyRegisterDto verifyRegister) {
        R verifyr = loginService.mayRegisterVerify();
        if (verifyr.getCode()!=200){
            return verifyr;
        }

        R r = iUserService.verifyRegister(verifyRegister);
        return r;
    }

    @PostMapping("/test")
    @Operation(summary = "测试")
    @SysLogAnnotation(operModul = "用户管理>>登陆管理", operType = "新增", operDesc = "测试接口")
    public R test(HttpServletRequest httpServletRequest) {

        UserAgent userAgent = UserAgent.parseUserAgentString(httpServletRequest.getHeader("User-Agent"));
        //获取IP地址
        String ip = IpUtil.getIpAddress(httpServletRequest);
        System.out.println(ip);
        //获取操作系统
        String osName = userAgent.getOperatingSystem().getName();
        System.out.println(osName);
        //获取浏览器类型
        String browser = userAgent.getBrowser().getName();
        System.out.println(browser);
        //获取登录地址
        String location = AddressUtil.getAddressByIP(ip);
        System.out.println(location);

        return R.success();
    }
    @PostMapping("/getSelfMag")
    @Operation(summary = "获取自己的登陆信息",description = "登陆成功后第一时间通过token调取，可以获得用户自己的信息")
    public R getSelfMsg() {
        Integer userId = SecurityUtils.getUserId();
        return iUserService.getUserMsgById(userId);
    }
    @PostMapping("/userList")
    @Operation(summary = "用户列表",description= "获取用户列表")
    public R userList(@RequestBody @Validated UserParam userParam) {
        return iUserService.getUserList(userParam);
    }

    @PostMapping("/userMsg")
    @Operation(summary = "用户信息",description= "获取用户详细信息")
    public R userMsg(@RequestParam("userId")Integer userId) {
        return iUserService.getUserMsgById(userId);
    }



    @PostMapping("/deleteUser")
    @Operation(summary = "删除用户",description= "通过用户id删除用户")
    public R delectUserById(@RequestParam("userId")Integer userId) {
        return iUserService.delectUserById(userId);
    }


}
