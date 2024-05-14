package com.example.todo2.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class JWTUtil {

    @Value("${com.example.jwt.secret}")
    private String key;

    public String generateToken(Map<String, Object> valueMap, int days){
        log.info("generateKey..." + key);

        //헤더
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload
        Map<String, Object> payloads = new HashMap<>();
        payloads.putAll(valueMap);

        //유효 기간
        int time = (1) * days;

        String jwtStr = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(time).toInstant()))  //개발 완료 후 plusDays()
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();
        return jwtStr;
    }

    public Map<String, Object> validateToken(String token) throws JwtException {
        Map<String, Object> claim = null;

        claim = Jwts.parser()
                .setSigningKey(key.getBytes()) //set key
                .parseClaimsJws(token) //파싱 및 검증, 실패 시 에러
                .getBody();

        return claim;
    }
}
