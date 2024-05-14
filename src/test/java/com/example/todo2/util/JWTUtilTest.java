package com.example.todo2.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class JWTUtilTest {
    @Autowired
    private JWTUtil jwtUtil;

    /**
     * JWT 생성(JWTUtil)
     * 검사 (jwt.io)
     * validateToken()
     */
    @Test
    void testGenerate() {
        Map<String, Object> claimMap = Map.of("mid", "ABCDE");
        String jwtStr = jwtUtil.generateToken(claimMap, 1);

        log.info(jwtStr);
    }

    @Test
    void testValidate(){
        String jwtStr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MTU2NzE2NDYsIm1pZCI6IkFCQ0RFIiwiaWF0IjoxNzE1NjcxNTg2fQ.57_MzEqpQMcsSKkqmL4XVy1ZOsE_2xdkw863P4XFE-w";
        Map<String, Object> claim = jwtUtil.validateToken(jwtStr);
        log.info(claim);
    }

    @Test
    void testAll(){
        String jwtStr = jwtUtil.generateToken(Map.of("mid", "AAAA", "email", "aaa@bbb.com"), 1);
        log.info(jwtStr);
        Map<String, Object> claim = jwtUtil.validateToken(jwtStr);
        log.info("MID: " + claim.get("mid"));
        log.info("EMAIL: " + claim.get("email"));
    }
}