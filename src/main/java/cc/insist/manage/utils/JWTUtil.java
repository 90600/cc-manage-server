package cc.insist.manage.utils;

import cc.insist.manage.model.dto.UserInfoDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @Author: cc
 * @Date: 2022/2/10 2:04 PM
 * @Description: jwt
 */
@Slf4j
public class JWTUtil {


    /**
     * token 过期时间，正常是7天，方便测试我们改为70
     */
    private static final long EXPIRE = 1000 * 60 * 60 * 24 * 7 * 10;

    /**
     * 加密的秘钥
     */
    private static final String SECRET = "cc.insist";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "insisting";

    /**
     * subject
     */
    private static final String SUBJECT = "insist";


    /**
     * 根据用户信息，生成令牌
     *
     * @param userInfoDTO
     * @return
     */
    public static String geneJsonWebToken(UserInfoDTO userInfoDTO) {
        if (userInfoDTO == null) {
            throw new NullPointerException("userInfoDTO为空");
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                //payload
//                .claim("head_img", loginUser.getHeadImg())
//                .claim("id", loginUser.getId())
                .claim("username", userInfoDTO.getUsername())
                .claim("id", userInfoDTO.getId())
//                .claim("mail", loginUser.getMail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();

        token = TOKEN_PREFIX + token;
        return token;
    }


    /**
     * 校验token的方法
     *
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            return claims;
        } catch (Exception e) {
            log.info("jwt token解密失败");
            return null;
        }
    }
}
