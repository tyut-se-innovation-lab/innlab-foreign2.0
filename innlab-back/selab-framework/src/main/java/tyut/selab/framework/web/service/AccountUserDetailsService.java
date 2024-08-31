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
import tyut.selab.framework.domain.entity.PortEntity;
import tyut.selab.framework.domain.entity.RoleEntity;
import tyut.selab.framework.domain.entity.UserEntity;
import tyut.selab.framework.domain.model.LoginUser;
import tyut.selab.framework.mapper.PortMapper;
import tyut.selab.framework.mapper.RoleMapper;
import tyut.selab.framework.mapper.UserMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PortMapper portMapper;
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
        LoginUser loginUser = new LoginUser(userEntity.getUserId(),null,userEntity.getUserState(),userEntity.getUserAccount(), userEntity.getUserPassword(), EnumUtils.getDepartmentNameById(String.valueOf(userEntity.getUserDepartment())),userEntity.getUserNickname(),null,null);
        RoleEntity roleEntity = roleMapper.selectById(userEntity.getRoleId());
        QueryWrapper<PortEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("port_id","select port_id from tyut_role_port_tag where role_id = '"+userEntity.getRoleId()+"'");
        List<PortEntity> portEntityList = portMapper.selectList(queryWrapper);
        Set<String> portSet = portEntityList.stream()
                .map(PortEntity::getPortKey)  // 使用方法引用获取name字段的值
                .collect(Collectors.toSet());  // 将结果收集到Set集合中
        loginUser.setPermission(roleEntity.getRoleKey());
        loginUser.setPort(portSet);
        return loginUser;
    }

}