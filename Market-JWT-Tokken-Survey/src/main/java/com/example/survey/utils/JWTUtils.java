package com.example.survey.utils;

import com.example.survey.model.BoardVO;
import com.example.survey.model.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    final String key = "JWT";

    public static void main(String[] args) throws UnsupportedEncodingException {
        JWTUtils testJWT = new JWTUtils();

        String jwt = testJWT.createToken();
        System.out.println(jwt);

        Map<String, Object> claimMap = testJWT.verifyJWT(jwt);
        System.out.println(claimMap); // 토큰이 만료되었거나 문제가있으면 null
    }

    //토큰 생성
    public String createToken() {

        //Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        UserVO userVO = new UserVO();
        BoardVO boardVO = new BoardVO();

        //payload 부분 설정
        Map<String, Object> payloads = new HashMap<>();
        //payloads.put("data", "My JWT");
        payloads.put("seq", userVO.getSeq());
        payloads.put("name", userVO.getName());
        payloads.put("email", userVO.getEmail());
        payloads.put("password", userVO.getPassword());
        payloads.put("last_Update", userVO.getLastUpdate());
        payloads.put("event_seq", userVO.getEventSeq());

        /**
         * 1. 토큰 검증
         *  1-1. 토큰내의 사용자 정보를 이용하여 설문조사를 완료한건지 여부를 확인하여
         *  1-2. 이미 처리된 설문조사이면 '감사합니다' 페이지로 이동
         *  1-3. 미 처리 사항이면 설문조사 응답 페이지로 이동.
         *
         *  jwt 이용하여 토큰 발급
         *  1. add claim : user_id, expired_date, order_num, purchase_date, event_id
         *
         */

        //Long expiredTime = 1000 * 60L * 60L * 2L; // 토큰 유효 시간 (2시간)
        Long expiredTime = 30 * 60 * 1000L; // 토큰 유효 시간 (30분)

        Date ext = new Date(); // 토큰 만료 시간
        ext.setTime(ext.getTime() + expiredTime);

        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                //.setSubject(userVO.getEmail()) // 토큰 용도(제목)
                .setExpiration(ext) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, key.getBytes()) // HS256과 Key로 Sign
                .compact(); // 토큰 생성

        return jwt;
    }

    //토큰 검증
    public Map<String, Object> verifyJWT(String jwt) throws UnsupportedEncodingException {
        Map<String, Object> claimMap = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key.getBytes("UTF-8")) // Set Key
                    .parseClaimsJws(jwt) // 파싱 및 검증, 실패 시 에러
                    .getBody();

            claimMap = claims;

        } catch (ExpiredJwtException e) { // 토큰이 만료되었을 경우
            System.out.println(e);
        } catch (Exception e) { // 그외 에러났을 경우
            System.out.println(e);
        }
        return claimMap;
    }
}
