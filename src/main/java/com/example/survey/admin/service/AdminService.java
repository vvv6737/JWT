package com.example.survey.admin.service;

import com.example.survey.admin.mapper.AdminMapper;
import com.example.survey.admin.model.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminMapper adminMapper;

    //관리자 로그인창
    public AdminVO adminLogin(AdminVO adminVO) {
        return adminMapper.adminLogin(adminVO);
    }
}
