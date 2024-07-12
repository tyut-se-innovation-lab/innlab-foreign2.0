package tyut.selab.framework.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tyut.selab.common.exception.ServiceException;
import tyut.selab.common.utils.EnumUtils;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.framework.domain.entity.UserEntity;
import tyut.selab.framework.domain.model.LoginUser;
import tyut.selab.framework.mapper.UserMapper;

/**
 * @ClassName: AccountUserDetailsService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 18:56
 * @Version: 1.0
 **/
@Service
@Slf4j
public class AccountUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
//    @Autowired
//    private RoleMapper roleMapper;
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", account);
        UserEntity userEntity = userMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNull(userEntity)){
            log.info("登录用户：{} 不存在.", account);
            throw new ServiceException("对不起，您的账号：" + account + " 不存在");
        }else  if(!userEntity.getUserState()){
            log.info("登录用户：{} 已被停用.", account);
            throw new ServiceException("对不起，您的账号：" + account + " 已停用");
        }
        return createLoginUser(userEntity);
    }
    public UserDetails createLoginUser(UserEntity userEntity) {
        LoginUser loginUser = new LoginUser();
        return new LoginUser(userEntity.getUserId(),null,userEntity.getUserState(),userEntity.getUserAccount(), userEntity.getUserPassword(), EnumUtils.getDepartmentNameById(String.valueOf(userEntity.getUserDepartment())),null,null);
    }

}