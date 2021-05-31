package com.example.survey.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*HttpSession session = request.getSession();
        SampleVO sampleVO = (SampleVO) session.getAttribute("sampleVO");

        if (sampleVO == null) {
            response.sendRedirect("/adminIndex");
            return false;
        }
        log.info("preHandle");*/


        // 1. 설문조사 페이지 판별 ( 설문조사 화면 진입의 경우만 )
        // 2. 세션에서 유저 정보를 가져온다.
        // 2-1. 유저 정보가 존재하지 않는 경우 ( 해당정보없음 화면으로 리다이렉션 )
        // 2-2. 유저 정보가 존재할 경우, 유저 Seq로 설문 조사 테이블을 조회한다. ( 해당 유저가 참여한 설문조사 )
        // 3-1. 유저가 참여한 설문조사 seq와 현재 진입한 설문조사 seq가 동일할 경우, 이미 참여했으므로 설문조사 목록으로 리다이렉션한다.
        // 3-2. 유저가 참여한 설문조사 seq와 현재 진입한 설문조사 seq가 동일하지 않은 경우, 참여하지 않았으므로 그대로 바이패스.

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        //log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}