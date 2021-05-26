package com.example.survey.service;

import com.example.survey.VO.User;
import com.example.survey.mapper.UserMapper;
import com.example.survey.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //로직 처리 : @Service (서비스 레이어, 내부에서 자바 로직을 처리함)
public class UserService {

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
}