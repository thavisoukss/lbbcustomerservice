package com.lbb.customer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class DecodeTokenV2 {

    public DecodeTokenObject peekFromHeader(String authHeader) throws JsonProcessingException {
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            throw new IllegalArgumentException("Authorization header หายไปหรือผิดรูปแบบ");
//        }
        String token;

//        if (authHeader != null && !authHeader.isBlank()) {

            if (authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            } else {
                token = authHeader;  // รับ token แบบดิบๆ
            }

            String[] parts = token.split("\\.");

            String payloadJson = new String(
                    Base64.getUrlDecoder().decode(parts[1]),
                    StandardCharsets.UTF_8
            );

            Map<String, Object> claims = new ObjectMapper()
                    .readValue(payloadJson, new TypeReference<Map<String, Object>>() {
                    });

            DecodeTokenObject data = new DecodeTokenObject();
            data.setUserId((String) claims.get("user-id"));
            data.setUserPhone((String) claims.get("user-phone"));
            data.setUserRole((String) claims.get("user-role"));
            data.setIsu((String) claims.get("iss"));
            data.setSub((String) claims.get("sub"));
            data.setSid((String) claims.get("sid"));
            data.setType((String) claims.get("type"));

            Object aud = claims.get("aud");
            data.setAud(aud instanceof List<?> l && !l.isEmpty() ? l.get(0).toString()
                    : aud != null ? aud.toString() : null);

            Object exp = claims.get("exp");
            Object iat = claims.get("iat");
            if (exp instanceof Number n) data.setExp((int) n.longValue());
            if (iat instanceof Number n) data.setIat((int) n.longValue());

            return data;
        }


}
