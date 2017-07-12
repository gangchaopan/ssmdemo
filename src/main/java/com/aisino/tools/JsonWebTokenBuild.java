package com.aisino.tools;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by gangchaopan on 2017/7/12.
 */
public class JsonWebTokenBuild {
    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";

    private static Logger logger = LoggerFactory.getLogger(JsonWebTokenBuild.class);

    public static Date buildTime(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date exp = new Date();
        try {
            exp = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(exp);
        return exp;
    }


    /**
     * 生产JWT
     * @param exp
     * @param map
     * @return
     * https://tools.ietf.org/html/draft-ietf-oauth-json-web-token-32
     */
    public static  String buildJWT(Date exp,Map map){

        try {
            Algorithm algorithm =  Algorithm.HMAC256(SECRET);
            Date currentDate = new Date();
            String token = JWT.create()
                    .withClaim("uid", (String) map.get("uid"))
                    .withClaim("isAdmin",(String) map.get("isAdmin"))
                    .withExpiresAt(exp)         //过期时间
                    .withIssuedAt(currentDate)  //签发时间
                    .withNotBefore(currentDate) //token在此时间之前不能被接收处理
                    .withIssuer("auth0")        //签发人
                    .sign(algorithm);
            logger.info("构建token成功");
            return token;
        }catch(Exception e){
            logger.info("构建token失败{}",e);
        }
        return null;
    }


    /**
     * 校验token
     * @param token
     * @return
     */
    public static String verify(String token) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String result=null;
        logger.info("token值{}",token);
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier =  JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            Map<String, Claim> payloadClaims = jwt.getClaims();
            Claim uid = payloadClaims.get("uid");

            logger.info("当前登录用户{}",uid.asString());
            result= mapper.writeValueAsString("校验toke成功");

        }catch(UnsupportedEncodingException e){
            logger.info("校验token失败{}",e);
            result= mapper.writeValueAsString("校验token失败");
        }catch(TokenExpiredException e){
            logger.info("token验证失败,token已过期",e);
            result= mapper.writeValueAsString("token验证失败,token已过期");

        }catch(JWTVerificationException e){
            result= mapper.writeValueAsString("token非法，请重新登录");
            logger.info("token非法，请重新登录",e);

        }catch(Exception e){
            result= mapper.writeValueAsString("token异常，请重新登录");
            logger.info("token异常，请重新登录",e);
        }

        return result;
    }
}
