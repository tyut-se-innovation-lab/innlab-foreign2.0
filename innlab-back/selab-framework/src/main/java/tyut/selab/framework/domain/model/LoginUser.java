package tyut.selab.framework.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @ClassName: LoginUser
 * @Description:登陆用户信息
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 18:00
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户唯一标识
     */
    private String token;

    private boolean userState;

    private String userAccount;

    private String userPassword;

    private String department;

    private String userNickName;

    /**
     * 权限
     */
    private String permission;

    /**
     * 接口列表
     */
    private Set<String> interfaces;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return userPassword;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return userAccount;
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return userState;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return userState;
    }
}
