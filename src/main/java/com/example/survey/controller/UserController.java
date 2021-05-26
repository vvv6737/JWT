package com.example.survey.controller;

import com.example.survey.admin.model.AdminVO;
import com.example.survey.model.UserVO;
import com.example.survey.service.UserService;
import com.example.survey.utils.SessionUtils;
import com.example.survey.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    final String key = "JWT";

    @Autowired
    UserService userService;

    //유저목록
    @GetMapping(value = "/userlist")
    public String userlist(Model model, HttpServletRequest request) throws UnsupportedEncodingException {

        UserVO userVO = new UserVO();

        List<UserVO> list =  userService.listUser(userVO);
        model.addAttribute("list", list);

        AdminVO adminVO = SessionUtils.getAdmin(request);
        model.addAttribute("admin", adminVO);

        if (adminVO != null) {
            model.addAttribute("admin", adminVO);
            return "pages/user/userList";
        }
        return "redirect:/adminloginform";
    }

    @PostMapping("/userlist/addData")
    public String insertdata(UserVO userVO, Model model, HttpServletRequest request, HttpServletResponse response) {
        String password = userVO.getPassword();
        String shaPass = null;
        try {
            shaPass = StringUtils.sha256(password);
            userVO.setPassword(shaPass);
            UserVO modelVO = userService.userInsert(userVO);
            log.info("## seq: " + modelVO.getSeq());
            return "redirect:/userlist";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "redirect:/userlist";
        }
    }

    //회원가입
    @GetMapping(value = "/register")
    public String register(UserVO userVO, Model model, HttpServletRequest request) {
        return "pages/user/Register";
    }

    //회원가입 폼
    @PostMapping(value = "/register")
    public String registerInsert(UserVO userVO, Model model, HttpServletRequest request) {

        String password = userVO.getPassword();
        String shaPass = null;
        try {
            shaPass = StringUtils.sha256(password);
            userVO.setPassword(shaPass);
            UserVO modelVO = userService.userInsert(userVO);
            log.info("## seq: " + modelVO.getSeq());
            return "redirect:/login";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "redirect:/register";
        }
    }

    //로그아웃
    @RequestMapping(value="/login/logout",method= RequestMethod.GET)
    private String getLogout(HttpSession session) throws Exception{
        log.info("getLogout...");
            session.invalidate();
        return "redirect:/surveylist/1";
    }

    //수정.
    @PostMapping("/userlist/editData")
    public String updatedata(UserVO userVO, HttpServletRequest request, HttpServletResponse response){
        UserVO modelVO = userService.userUpdate(userVO);
        return "redirect:/userlist";
    }

    //삭제
    @PostMapping("/userlist/deleteData")
    private String deletedata(UserVO userVO, HttpServletRequest request, HttpServletResponse response) {
        UserVO modelVO = userService.userDelete(userVO);
        return "redirect:/userlist";
    }
}