package com.ourprojmgr.demo.controller;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器，用于检查用户是否登录
 * @author 朱华彬
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法，直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //若该控制器方法上带有 @LoginRequired 注解，则检查 token 是否有效
        if (handlerMethod.hasMethodAnnotation(LoginRequired.class)) {
            String token = request.getHeader("Authorization");
            //TODO zhb: 检查 token 是否有效
            return true;
        } else {
            return true;
        }
    }
}
