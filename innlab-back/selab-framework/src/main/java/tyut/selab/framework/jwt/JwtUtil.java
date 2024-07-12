package tyut.selab.framework.jwt;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tyut.selab.common.domain.ResponseCode;
import tyut.selab.common.exception.BaseException;
import tyut.selab.common.utils.EncryptUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName: JwtUtil
 * @Description: jwt工具类
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 18:13
 * @Version: 1.0
 **/
@Component
@Slf4j
@Data
public class JwtUtil {
    // 令牌秘钥
    @Value("${token.secret}")
    private String SECRET;
    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private Integer EXPIRE;
    @Value("${token.header}")
    private String HEADER;

    /**
     * 生成jwt token
     */
    public String gerHeader(){
        return HEADER;
    }
    public String generateToken(String token) {
        String str = EncryptUtils.Base64Encrypt(token);
        log.info("toke生成字符串:{}",str);
        SecretKey signingKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        //过期时间
        LocalDateTime tokenExpirationTime = LocalDateTime.now().plusMinutes(EXPIRE);
        return Jwts.builder()
                .signWith(signingKey, Jwts.SIG.HS512)
                .header().add("typ", "JWT").and()
                .issuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .subject(str)
                .expiration(Timestamp.valueOf(tokenExpirationTime))
                .claims(Map.of("token", str))
                .compact();
    }

    public String getToken(String token){
        if (StrUtil.isBlankOrUndefined(token)) {
            throw new BaseException(ResponseCode.Bad_Request, "token异常");
        }
        log.info(token);
        Claims claims = getClaimsByToken(token);
        if (claims == null) {
            throw new BaseException(ResponseCode.Bad_Request, "token异常");
        }
        if (this.isTokenExpired(claims.getExpiration())) {
            throw new BaseException(ResponseCode.Bad_Request, "token已过期");
        }
        String tokenkey = EncryptUtils.Base64Decrypt(claims.getSubject());
        return tokenkey;
    }

    /**
     * 获得token中的自定义信息,一般是获取token的username，无需secret解密也能获得
     * @param token
     * @param filed
     * @return
     */
    public String getClaimFiled(String token, String filed){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(filed).asString();
        } catch (JWTDecodeException e){
            log.error("JwtUtil getClaimFiled error: ", e);
            return null;
        }
    }
    public Claims getClaimsByToken(String token) {
        SecretKey signingKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        Claims claims;
        try{ claims = Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }
    /**
     * 检查token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

}
