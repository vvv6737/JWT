package com.example.survey.service;

import com.example.survey.mapper.UserMapper;
import com.example.survey.model.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service //로직 처리 : @Service (서비스 레이어, 내부에서 자바 로직을 처리함)
public class UserService {
    final String key = "JWT";

    @Autowired //필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
    UserMapper userMapper;

    //리스트 - 자바의 자료형. 삽입과 삭제로 원하는대로 크기를 변경이 가능함.
    public List<UserVO> listUser(UserVO model) {
        return userMapper.listUser(model);
    }

    //입력
    public UserVO userInsert(UserVO userVO) {
        userMapper.userInsert(userVO);
        return userVO;
    }

    //수정
    public UserVO userUpdate(UserVO userVO) {
        userMapper.userUpdate(userVO);
        return userVO;
    }

    //삭제
    public UserVO userDelete(UserVO userVO) {
        userMapper.userDelete(userVO);
        return userVO;
    }

    //설문조사 유저 정보
    public UserVO urlTest(String id) {
        return userMapper.urlTest(id);
    }

    /**
     * 설문조사 참여여부 판단
     * @param eventSeq 참여한 이벤트 시퀀스
     * @param userVO
     * @return boolean 참여했다면 true, 아직 미참여인 경우 false
     */
    public UserVO isFinishSurvey(String id) {
        return userMapper.isFinishSurvey(id);
    }

    //토큰 생성
    public String createToken(UserVO userVO) {

        //Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload 부분 설정
        Map<String, Object> payloads = new HashMap<>();
        //payloads.put("data", "My JWT");
        payloads.put("seq", userVO.getSeq());
        payloads.put("id", userVO.getId());
        payloads.put("name", userVO.getName());
        payloads.put("email", userVO.getEmail());
        payloads.put("tel", userVO.getTel());
        payloads.put("eventSeq", userVO.getEventSeq());

        //Long expiredTime = 1000 * 60L * 60L * 2L; // 토큰 유효 시간 (2시간)
        Long expiredTime = 30 * 60 * 1000L; // 토큰 유효 시간 (30분)


        Date ext = new Date(); // 토큰 만료 시간
        ext.setTime(ext.getTime() + expiredTime);

        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                //.setSubject(sampleVO.getEmail()) // 토큰 용도(제목)
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