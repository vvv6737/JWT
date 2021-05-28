package com.example.survey.utils;

import com.example.survey.admin.model.AdminVO;
import com.example.survey.admin.model.EventVO;
import com.example.survey.model.ProductVO;
import com.example.survey.model.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    final static String SESSION_USER_KEY = "USER_KEY";
    final static String SESSION_ADMIN_KEY = "ADMIN_KEY";

    final static String SESSION_EVENT_LIST = "EVENT_LIST";
    final static String SESSION_PRODUCT_LIST = "PRODUCT_LIST";

    public static boolean setUser(UserVO user, HttpServletRequest request){
        if (user == null) return false;
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_USER_KEY, user);
        return true;
    }

    public static boolean setAdmin(AdminVO admin, HttpServletRequest request){
        if (admin == null) return false;
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_ADMIN_KEY, admin);
        return true;
    }

    //이벤트 리스트
    public static boolean setEventList(EventVO eventVO, HttpServletRequest request){
        if (eventVO == null) return false;
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_EVENT_LIST, eventVO);
        return true;
    }

    //상품 리스트
    public static boolean setProductList(ProductVO productVO, HttpServletRequest request){
        if (productVO == null) return false;
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_PRODUCT_LIST, productVO);
        return true;
    }

    public static UserVO getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (UserVO) session.getAttribute(SESSION_USER_KEY);
    }

    public static AdminVO getAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (AdminVO) session.getAttribute(SESSION_ADMIN_KEY);
    }

    //이벤트 리스트
    public static EventVO getEventList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (EventVO) session.getAttribute(SESSION_EVENT_LIST);
    }

    //상품 리스트
    public static ProductVO getProductList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (ProductVO) session.getAttribute(SESSION_PRODUCT_LIST);
    }
}
