package tyut.selab.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.EnumUtils;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.common.utils.StringUtils;
import tyut.selab.common.utils.http.ServletUtils;
import tyut.selab.framework.domain.dto.*;
import tyut.selab.framework.domain.dto.param.UserParam;
import tyut.selab.framework.domain.entity.*;
import tyut.selab.framework.domain.model.LoginUser;
import tyut.selab.framework.domain.model.LoginUserToken;
import tyut.selab.framework.domain.vo.UserMsgVo;
import tyut.selab.framework.jwt.security.AuthenticationContextHolder;
import tyut.selab.framework.mapper.*;
import tyut.selab.framework.service.IResourceService;
import tyut.selab.framework.service.IUserService;
import tyut.selab.framework.web.SecurityUtils;
import tyut.selab.framework.web.service.LoginService;
import tyut.selab.framework.web.service.TokenService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName: UserServiceimpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 17:40
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserMsgMapper userMsgMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private InvitationMapper invitationMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private IResourceService iResourceService;
    @Override
    public R login(LoginDto loginDto){
        Authentication authentication = null;
        try{
//            // 创建一个认证令牌，包含用户名和密码
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getAccount(), loginDto.getPassword());
//            // 调用 AuthenticationManager 进行身份验证
            AuthenticationContextHolder.setContext(authenticationToken);
//            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            return loginService.addLoginFailFrequency(loginDto.getAccount());
        }
        // 如果身份验证成功，可以从认证对象中获取用户详细信息
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            Map<String, Object> map = getMap(loginUser);
            return R.success(map);
    }
    private Map<String, Object> getMap(LoginUser loginUser){
        String token = tokenService.createToken(loginUser);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        UserEntity userEntity = userMapper.selectById(loginUser.getUserId());
        map.put("lastLoginTime",userEntity.getLastLoginTime());
        map.put("lastLoginIp",userEntity.getLastLoginIp());
        map.put("lastLoginLocation",userEntity.getLastLoginLocation());
        userEntity.setLoginTotal(userEntity.getLoginTotal()+1);
        LoginUserToken loginUserToken =tokenService.getLoginUserToken1(token);
        userEntity.setLastLoginIp(loginUserToken.getIpaddr());
        userEntity.setLastLoginLocation(loginUserToken.getLoginLocation());
        userEntity.setLastLoginTime(LocalDateTime.now());
        userMapper.updateById(userEntity);
        return map;
    }

    @Override
    public R logoutUser(){
        HttpServletRequest request = ServletUtils.getRequest();
        LoginUserToken loginUserToken =  tokenService.getLoginUserToken(request);
        tokenService.deleteToken(loginUserToken);
        return R.success("退出成功！");
    }
    @Override
    public R addUser(AddUserDto addUserDto){
        QueryWrapper<InvitationEntity> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("Invitation_code",addUserDto.getInvitationCode())
                .eq("state",true);
        InvitationEntity invitationEntity = invitationMapper.selectOne(queryWrapper3);
        if (ObjectUtils.isNull(invitationEntity)){
            return R.error("邀请码错误！");
        }
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", addUserDto.getUserAccount());
        UserEntity userEntity = userMapper.selectOne(queryWrapper);
        QueryWrapper<UserEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_email", addUserDto.getUserEmail());
        UserEntity userEntity1 = userMapper.selectOne(queryWrapper1);
        if (ObjectUtils.isNotNull(userEntity)) {
            return R.error("账号已注册！");
        }
        if(ObjectUtils.isNotNull(userEntity1)){
            return R.error("邮箱已注册！");
        }
        userEntity = get1(addUserDto);
        userEntity.setRoleId(invitationEntity.getRoleId());
        userEntity.setUserDepartment(invitationEntity.getDepartmentId());
        userMapper.insert(userEntity);
        UserEntity userEntity2 = userMapper.selectOne(queryWrapper);
        UserMsgEntity userMsgEntity =get2(userEntity2);
        userMsgMapper.insert(userMsgEntity);
        return R.success("注册成功！");
    }
    private UserEntity get1(AddUserDto addUserDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserAccount(addUserDto.getUserAccount());
        userEntity.setUserEmail(addUserDto.getUserEmail());
        userEntity.setUserNickname(addUserDto.getUserNickname());
        userEntity.setUserPassword(SecurityUtils.encryptPassword(addUserDto.getUserPassword()));
        userEntity.setUserState(true);
        userEntity.setLoginTotal(0);
        userEntity.setDelFlag(0);
        return userEntity;
    }
    private UserMsgEntity get2(UserEntity userEntity){
        UserMsgEntity userMsgEntity = new UserMsgEntity();
        userMsgEntity.setUserId(userEntity.getUserId());
        userMsgEntity.setUserEmail(userEntity.getUserEmail());
        userMsgEntity.setDelFlag(0);
//        userMsgEntity.setUserPhone(addUserDto.getUserPhone());
        return userMsgEntity;
    }
    @Override
    public R verifyRegister(VerifyRegisterDto verifyRegister){
        QueryWrapper<InvitationEntity> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("Invitation_code",verifyRegister.getInvitationCode())
                .eq("state",true);
        InvitationEntity invitationEntity = invitationMapper.selectOne(queryWrapper3);
        if (ObjectUtils.isNull(invitationEntity)){
            return R.error("邀请码错误！");
        }
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_email", verifyRegister.getUserEmail());
        UserEntity userEntity = userMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotNull(userEntity)){
            return R.error("该邮箱已注册！");
        }
        QueryWrapper<UserEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_account",verifyRegister.getUserAccount());
        UserEntity userGmEntity1 = userMapper.selectOne(queryWrapper1);
        if (ObjectUtils.isNotNull(userGmEntity1)){
            return R.error("该账号已注册！");
        }
        return loginService.addRegisterVerify(verifyRegister.getUserAccount(),verifyRegister.getUserEmail());

    }

    @Override
    public R getUserList(UserParam userParam){
        Page<UserEntity> page = new Page<>(userParam.getPageNum(),userParam.getPageSize());
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(userParam.getUserAccount()),"user_account",userParam.getUserAccount());
//                .eq(ObjectUtils.isNotNull(userParam.getUserDepartment()),"user_department", EnumUtils.getDepartmentIdByName(userParam.getUserDepartment()));
        if (ObjectUtils.isNotNull(userParam.getUserDepartment())){
            queryWrapper.eq("user_department", EnumUtils.getDepartmentIdByName(userParam.getUserDepartment()));
        }
        IPage<UserEntity> userIPage = userMapper.selectPage(page, queryWrapper);
        userIPage.getRecords().forEach(userEntity -> {
            if (ObjectUtils.isNotNull(userEntity.getUserDepartment())){
                userEntity.setUserDepartmentName(EnumUtils.getDepartmentNameById(String.valueOf(userEntity.getUserDepartment())));
            }
        });
        return R.success(userIPage);
    }
    @Override
    public R getUserMsgById(Integer userId) throws ExecutionException, InterruptedException {
        UserEntity userEntity = userMapper.selectById(userId);
        UserMsgEntity userMsgEntity = userMsgMapper.selectById(userId);
        UserMsgVo userMsgVo = get1(userEntity,userMsgEntity);
        return R.success(userMsgVo);
    }


    @Override
    public R updateUser(UpdateUserDto updateUserDto){
        UserMsgEntity userMsgEntity = userMsgMapper.selectById(updateUserDto.getUserId());
        if (ObjectUtils.isNull(userMsgEntity)){
            return R.error("用户不存在！");
        }
        if (ObjectUtils.isNotNull(updateUserDto.getUserAvatar())){
            ResourceEntity resourceEntity = resourceMapper.selectById(updateUserDto.getUserAvatar());
            if (ObjectUtils.isNotNull(resourceEntity)){
                userMsgEntity.setUserAvatar(updateUserDto.getUserAvatar());
                userMsgMapper.updateById(userMsgEntity);
                return R.success("头像修改成功！");
            }else {
                return R.error("图片不存在！");
            }
        }
        userMsgEntity.setUserPhone(updateUserDto.getUserPhone());
        userMsgMapper.updateById(userMsgEntity);
        return R.success("修改成功！");
    }


    @Override
    public R updateUserPassword(UpdateUserPasswordDto updateUserPasswordDto){
        UserEntity userEntity = userMapper.selectById(updateUserPasswordDto.getUserId());
        if (ObjectUtils.isNull(userEntity)){
            return R.error("用户不存在！");
        }
        if (SecurityUtils.matchesPassword(updateUserPasswordDto.getOldUserPassword(),userEntity.getUserPassword())){
            userEntity.setUserPassword(SecurityUtils.encryptPassword(updateUserPasswordDto.getNewUserPassword()));
            userMapper.updateById(userEntity);
            return R.success("修改成功！");
        }else{
            return R.error("原密码错误！");
        }
    }


    @Override
    public R resetUserPassword(Integer userId){
        UserEntity userEntity = userMapper.selectById(userId);
        if (ObjectUtils.isNull(userEntity)){
            return R.error("用户不存在！");
        }
            userEntity.setUserPassword(SecurityUtils.encryptPassword("123456"));
            userMapper.updateById(userEntity);
            return R.success("修改成功！");
    }
    @Override
    public R delectUserById(Integer userId){
        userMapper.deleteById(userId);
        userMsgMapper.deleteById(userId);
        return R.success("删除成功！");
    }
    private UserMsgVo get1(UserEntity userEntity,UserMsgEntity userMsgEntity) throws ExecutionException, InterruptedException {
        UserMsgVo userMsgVo = new UserMsgVo();
        userMsgVo.setAccount(userEntity.getUserAccount());
        userMsgVo.setNickname(userEntity.getUserNickname());
        userMsgVo.setLastLoginIp(userEntity.getLastLoginIp());
        userMsgVo.setLastLoginTime(userEntity.getLastLoginTime());
        userMsgVo.setPhone(userMsgEntity.getUserPhone());
        userMsgVo.setEmail(userEntity.getUserEmail());
        userMsgVo.setLastLoginLocation(userEntity.getLastLoginLocation());
        userMsgVo.setDepartment(EnumUtils.getDepartmentNameById(String.valueOf(userEntity.getUserDepartment())));
        if (ObjectUtils.isNotNull(userMsgEntity.getUserAvatar())){
            R r = iResourceService.getResourceById(userMsgEntity.getUserAvatar());
            String avatarUrl = (String) r.getMessage();
            if (StringUtils.isNotBlank(avatarUrl)){
                userMsgVo.setAvatarUrl(avatarUrl);
            }else {
                userMsgVo.setAvatarUrl("https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/d809a05f94085f016d4ef0094eda7a221c3f4b102bb8b15c2dfe84ef76cc3ae2f6b012e6850a1be56ee5ddbf1274bcc9?pictype=scale&from=30013&version=3.3.3.3&fname=%E8%B5%84%E6%BA%90_13%20%285%29.png&size=750");
            }
        }else {
            userMsgVo.setAvatarUrl("https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/d809a05f94085f016d4ef0094eda7a221c3f4b102bb8b15c2dfe84ef76cc3ae2f6b012e6850a1be56ee5ddbf1274bcc9?pictype=scale&from=30013&version=3.3.3.3&fname=%E8%B5%84%E6%BA%90_13%20%285%29.png&size=750");
        }
        RoleEntity roleEntity = roleMapper.selectById(userEntity.getRoleId());
        userMsgVo.setRole(roleEntity.getRoleKey());
        return userMsgVo;
    }


}
