package com.example.survey.service;

import com.example.survey.admin.mapper.AdminMapper;
import com.example.survey.admin.model.AdminVO;
import com.example.survey.mapper.UserMapper;
import com.example.survey.model.UserVO;
import com.example.survey.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class LoginService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;

    //암호화
    public UserVO userCheck(UserVO userVO) throws NoSuchAlgorithmException {
        String password = userVO.getPassword();
        String shaPass = StringUtils.sha256(password);
        userVO.setPassword(shaPass);
        return userMapper.userCheck(userVO);
    }

    //관리자
    public AdminVO adminIdCheck(AdminVO adminVO) throws Exception{
        adminMapper.adminIdCheck(adminVO);
        return adminMapper.adminIdCheck(adminVO);
    }
}
