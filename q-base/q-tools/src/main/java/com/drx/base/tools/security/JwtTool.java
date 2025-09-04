package com.drx.base.tools.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTool {

    private static final String SECRET = "N6uAsGk2WXrLpYiyc90xhpc3zMdTFZgkVM0csk8KnQazLVz5huCVHZzC6714";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);


    private static final long EXPIRATION_MS = 3 * 24 * 60 * 60 * 1000;

    public static String createToken(Map<String, String> payload) {
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_MS);
        return JWT.create().withPayload(payload).withIssuedAt(issuedAt).withExpiresAt(expiresAt).sign(ALGORITHM);
    }


    public static Map<String, String> parseToken(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        Map<String, Claim> originalClaims = decodedJWT.getClaims();
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, Claim> entry : originalClaims.entrySet()) {
            String key = entry.getKey();
            Claim claim = entry.getValue();
            if (claim.isNull()) {
                result.put(key, null);
            } else {
                result.put(key, claim.asString());
            }
        }
        return result;
    }

}
