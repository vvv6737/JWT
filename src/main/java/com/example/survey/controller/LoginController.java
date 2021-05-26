package com.example.survey.controller;

import com.example.survey.admin.model.AdminVO;
import com.example.survey.model.UserVO;
import com.example.survey.service.LoginService;
import com.example.survey.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    //화면
    @GetMapping(value = "/login")
    public String login(Model model, HttpServletRequest request) {
        return "pages/user/login";
    }

    //로그인폼
    @PostMapping(value = "/login")
    public String loginCheck(UserVO userVO, HttpServletRequest request, RedirectAttributes attributes) {
        try {
            UserVO user = loginService.userCheck(userVO);

            if (SessionUtils.setUser(user, request)){
                return "redirect:/surveylist/1";
            } else {
                attributes.addFlashAttribute("msg", false);
                return "redirect:/login";
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "redirect:/login";
        }
    }

    //관리자 로그인폼
    @PostMapping(value = "/adminloginform")
    public String adminIdCheck(AdminVO adminVO, Model model, HttpServletRequest request, RedirectAttributes attributes) {

        HttpSession session = request.getSession();

        try {
            AdminVO admin = loginService.adminIdCheck(adminVO);

            if (SessionUtils.setAdmin(admin, request)){
                return "redirect:/adminindex";
            } else {
                session.setAttribute("admin", null);
                attributes.addFlashAttribute("msg", false);
                return "redirect:/adminloginform";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/adminloginform";
        }
    }
}

