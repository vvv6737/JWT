package com.example.survey.admin.controller;

import com.example.survey.admin.model.AdminVO;
import com.example.survey.admin.service.AdminService;
import com.example.survey.service.LoginService;
import com.example.survey.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    LoginService loginService;

    //관리자 홈
    @GetMapping("/adminindex")
    private String adminForm(Model model, HttpServletRequest request) {

        AdminVO admin = SessionUtils.getAdmin(request);
        model.addAttribute("admin", admin);

        return "pages/admin/adminIndex";
    }

    //로그인창
    @GetMapping("/adminloginform")
    private String getAdminLoginForm(Model model, HttpServletRequest request) {

        AdminVO admin = SessionUtils.getAdmin(request);
        model.addAttribute("admin", admin);

        return "pages/admin/adminloginform";
    }

    //로그아웃 GET
    @GetMapping(value="/adminlogout")
    private String getAdminLogout(HttpSession session) throws Exception{
        session.invalidate();
        return "redirect:/adminindex";
    }
}
