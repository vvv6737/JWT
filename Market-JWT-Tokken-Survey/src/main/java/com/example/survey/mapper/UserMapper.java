package com.example.survey.mapper;

import com.example.survey.model.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //패키지를 포함하는 클래스명 부분이 mapper xml 상의 namespace가 선택되고 인터페이스 메소드가 query id로 호출되는 방식
@Repository //db처리를 하기위해 사용, 외부I/O 처리 :  (퍼시스턴스 레이어, DB나 파일같은 외부 I/O 작업을 처리함)
public interface UserMapper {

	//sampleVO와 연결 listUser를 만들어놓는다.
    public List<UserVO> listUser(UserVO model);

    //입력
    int userInsert(UserVO userVO);

    //로그인
    UserVO userCheck(UserVO userVO);

    //수정
    int userUpdate(UserVO userVO);

    //삭제
    int userDelete(UserVO userVO);

    //설문조사 유저 정보
    UserVO urlTest(String id);

    UserVO isFinishSurvey(String id);
}
