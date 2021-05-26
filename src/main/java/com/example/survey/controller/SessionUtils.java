package com.example.survey.controller;

import com.example.survey.VO.User;
import com.example.survey.model.Membership;
import com.example.survey.model.Role;
import com.example.survey.model.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionUtils {

    final static String LOGIN_USER = "LOGIN_USER";

    /**
     * HttpSession에 사용자 정보 저장.
     *
     * @param request
     * @param user
     * @return
     */

    public static boolean setSessionUser(HttpServletRequest request, User user) {

        if (user == null) return false;
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(LOGIN_USER, user);
        return true;
    }

    /**
     * HttpSession 에서 사용자 정보를 조회한다.
     *
     * @param request
     * @return
     */
    public static User getSessionUser(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Object user = httpSession.getAttribute(LOGIN_USER);
        if (user == null) {
            return null;
        } else {
            return (User) user;
        }
    }

    //사용자 정보를 조회한다.
    public static UserVO getSessionSample(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Object sampleVO = httpSession.getAttribute(LOGIN_USER);
        if (sampleVO == null) {

            return null;
        } else {
            return (UserVO) sampleVO;
        }
    }

    /**
     * HttpSession에서 사용자 정보를 조회한다.
     *
     * @return 사용자 정보가 포함된 User를 반환한다.
     * 빈객체 User를 반환한다.
     */
    public static User getLoginUser(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Object user = httpSession.getAttribute(LOGIN_USER);
        if (user instanceof User) {
            return (User) user;
        } else {
            return new User();
        }
    }

    /**
     * 세션에서 로그인 사용자 정보를 조회하여 반환한다.
     * 정보조회 실패시 로그인화면으로 리다이렉트 처리함.
     */
    public static User getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        Object user = request.getSession().getAttribute(LOGIN_USER);
        if (user instanceof User) {
            return (User) user;
        } else {
            try {
                // 사용자 정보가 없다면 로그인 화면으로 이동다.
                response.sendRedirect("redirect:/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new User();
        }
    }

    /**
     * 회원등급 확인
     */
    public static String getUserMembership(HttpServletRequest request) {
        User user = getSessionUser(request);
        if (user != null) {
            return user.getMembership();
        } else {
            return Membership.FREE.getValue();
        }
    }

    /**
     * 사용자 어드민 권한 조회.
     * @param request
     * @return
     */
    public static Boolean isAdmin(HttpServletRequest request) {
        User user = getSessionUser(request);
        try {
            return user != null && Role.ADMIN.getValue().equals(user.getRole());
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 로그아웃 처리를 한다.
     */
    public static Boolean logout(HttpServletRequest request) {
        try {
            request.getSession().invalidate();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}