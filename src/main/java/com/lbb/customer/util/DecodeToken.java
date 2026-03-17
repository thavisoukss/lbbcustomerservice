package com.lbb.customer.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class DecodeToken {

    public  DecodeTokenObject decodeToken (Authentication authentication){
        DecodeTokenObject data = new DecodeTokenObject();
        Object details = authentication.getDetails();
        if (details instanceof Claims) {
            Claims claims = (Claims) details;
        String userId = (String) claims.get("user-id");
        String userPhone = (String) claims.get("user-phone");
        String userRole = (String) claims.get("user-role");
        String issuer = (String) claims.get("iss");

            String aud = (String) claims.get("aud");
            int exp = (Integer) claims.get("exp");
            int iat = (Integer) claims.get("iat");
            String sid = (String) claims.get("sid");
            String sub = (String) claims.get("sub");
            String type = (String) claims.get("type");

        data.setUserId(userId);
        data.setUserPhone(userPhone);
        data.setUserRole(userRole);
        data.setIsu(issuer);
        data.setAud(aud);
        data.setExp(exp);
        data.setIat(iat);
        data.setSid(sid);
        data.setSub(sub);
        data.setType(type);
        } else {
            System.out.println("ບໍ່ພົບຂໍ້ມູນ Claims ໃນ Authentication!");
        }
        System.out.println(data);
        return data;
    }





}
