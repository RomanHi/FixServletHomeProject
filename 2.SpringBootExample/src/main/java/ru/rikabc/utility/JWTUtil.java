package ru.rikabc.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @Author Roman Khayrullin on 28.04.2018
 * @Version 1.0
 */
@Service
public class JWTUtil {
    @Value("${secret.key}")
    private static String signingKey = "There's vomit on his sweater already, mom's spaghetti";

    public static String createToken(Long id) {
        try {
            Algorithm algorithmHS = Algorithm.HMAC256(signingKey);
            String token = JWT.create().withIssuer("auth0")
                    .withClaim("UserID", id)
                    .sign(algorithmHS);
            return token;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long verifyId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(signingKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("UserID").asLong();
        } catch (JWTVerificationException | UnsupportedEncodingException e) {
            return -1L;
        }
    }
}
