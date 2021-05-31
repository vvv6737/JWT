package com.example.survey.admin.mapper;

import com.example.survey.admin.model.AdminVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {

    //아이디 체크
    AdminVO adminIdCheck(AdminVO adminVO);

    //로그인
    AdminVO adminLogin(AdminVO adminVO);
}
