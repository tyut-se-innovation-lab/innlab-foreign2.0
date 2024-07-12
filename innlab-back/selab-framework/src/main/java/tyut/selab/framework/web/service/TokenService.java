package tyut.selab.framework.web.service;

import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tyut.selab.common.constant.KeyConstants;
import tyut.selab.common.utils.*;
import tyut.selab.common.utils.http.AddressUtil;
import tyut.selab.common.utils.http.IpUtil;
import tyut.selab.common.utils.http.ServletUtils;
import tyut.selab.framework.domain.model.LoginUser;
import tyut.selab.framework.domain.model.LoginUserToken;
import tyut.selab.framework.jwt.JwtUtil;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: TokenService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 22:17
 * @Version: 1.0
 **/
@Component
@Slf4j
public class TokenService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        LoginUserToken loginUserToken = getLoginUserToken(request);
        if (ObjectUtils.isNotNull(loginUserToken)) {
            LoginUser loginuser = getLoginUserByUserId(loginUserToken.getUserId());
            return loginuser;
        }
        return null;
    }

    /**
     * 获取用户Token身份信息
     *
     * @return 用户信息
     */
    public LoginUserToken getLoginUserToken(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            try {
                String tokenkey = EncryptUtils.Base64Decrypt(getKeyFromToken(token));
                log.info("tokenKey: "+getTokenKey(tokenkey));
                if (redisUtils.hasKey(getTokenKey(tokenkey))){
                    //获取用户id
                    LoginUserToken loginUserToken = (LoginUserToken) redisUtils.getCacheObject(getTokenKey(tokenkey));
                    return loginUserToken;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public LoginUserToken getLoginUserToken1(String token) {

        if (StringUtils.isNotEmpty(token)) {
            try {
                String tokenkey = EncryptUtils.Base64Decrypt(getKeyFromToken(token));
                log.info("tokenKey: "+getTokenKey(tokenkey));
                if (redisUtils.hasKey(getTokenKey(tokenkey))){
                    //获取用户id
                    LoginUserToken loginUserToken = (LoginUserToken) redisUtils.getCacheObject(getTokenKey(tokenkey));
                    return loginUserToken;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public LoginUserToken getLoginUserToken2(String token) {

        if (StringUtils.isNotEmpty(token)) {
            try {
                String tokenkey = token;
                if (redisUtils.hasKey(getTokenKey(tokenkey))){
                    //获取用户id
                    LoginUserToken loginUserToken = (LoginUserToken) redisUtils.getCacheObject(getTokenKey(tokenkey));
                    return loginUserToken;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUserByUserId(Integer userId) {

        String loginKey = getLoginKey(userId);
        log.info(loginKey);
        if (redisUtils.hasKey(loginKey)) {
            try {
                LoginUser loginuser = (LoginUser) redisUtils.getCacheObject(loginKey);
                return loginuser;
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser loginUser) {
        String tokenkey = RandomUtils.createCode(10);
        log.info("唯一标识符："+tokenkey);
        loginUser.setToken(tokenkey);
        //设置登录信息
        LoginUserToken loginUserToken = new LoginUserToken(loginUser);
        HttpServletRequest request = ServletUtils.getRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        //获取IP地址
        String ip = IpUtil.getIpAddress(request);
        //获取操作系统
        String osName = userAgent.getOperatingSystem().getName();
        //获取浏览器类型
        String browser = userAgent.getBrowser().getName();
        //获取登录地址
        String location = AddressUtil.getAddressByIP(ip);
        loginUserToken.setIpaddr(ip);
        loginUserToken.setBrowser(browser);
        loginUserToken.setLoginLocation(location);
        loginUserToken.setOs(osName);
        return refreshToken(loginUser, loginUserToken);
    }

    /**
     * 刷新令牌有效期
     *
     */
    public String refreshToken(LoginUser loginUser, LoginUserToken loginUserToken) {
        loginUserToken.setLoginTime(System.currentTimeMillis());
//        loginUser.setExpireTime(loginUserToken.getLoginTime() + 30* MILLIS_MINUTE);
//        loginUserToken.setExpireTime(loginUserToken.getLoginTime() + 30 * MILLIS_MINUTE);
        // 根据uuid将loginUser登录的缓存
        String userKey = getTokenKey(loginUser.getToken());
        log.info(userKey);
        redisUtils.setCacheObject(userKey, loginUserToken, 24, TimeUnit.HOURS);
        //设置loginUser缓存
        redisUtils.setCacheObject(getLoginKey(loginUser.getUserId()), loginUser, 24, TimeUnit.HOURS);

        String tokenkey = loginUserToken.getToken();
        log.info("tokenkey: "+tokenkey);
        return createToken(tokenkey);
    }

    /**
     * 删除用户身份信息Token
     */
    public void delLoginUserToken(String tokenkey) {
        if (StringUtils.isNotEmpty(tokenkey)) {
            String userKey = getTokenKey(tokenkey);
            redisUtils.deleteObject(userKey);
        }
    }
    /**
     * 删除用户身份信息LoginUser
     */
    public void delLoginUser(Integer userId) {
        if (ObjectUtils.isNotNull(userId)) {
            String userKey = getLoginKey(userId);
            redisUtils.deleteObject(userKey);
        }
    }
    /**
     * 删除令牌
     *
     */
    public void deleteToken(LoginUserToken loginUserToken) {
        delLoginUserToken(loginUserToken.getToken());
        delLoginUser(loginUserToken.getUserId());
    }

    /**
     * 从数据声明生成令牌
     *
     * @return 令牌
     */
    public String createToken(String tokenkey) {
        return jwtUtil.generateToken(tokenkey);
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims parseToken(String token) {
        return jwtUtil.getClaimsByToken(token);
    }

    /**
     * 从令牌中获取tokenkey
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getKeyFromToken(String token) {
        Claims claims = parseToken(token);
        log.info(String.valueOf(claims));
        log.info(claims.get("token", String.class));
        return claims.get("token", String.class);
    }
    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(jwtUtil.gerHeader());
        return token;
    }


        public String getTokenKey(String token) {
            return KeyConstants.LOGIN_TOKEN_KEY + token;
        }

    public String getLoginKey(Integer userId) {
        return KeyConstants.LOGIN_USER_KEY + userId;
    }
}
