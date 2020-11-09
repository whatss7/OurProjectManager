package com.ourprojmgr.demo.controller;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 参数分解器，提取用户信息
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    /**
     * 判断是否处理该参数分解
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(String.class)
                && parameter.hasParameterAnnotation(UserInRequest.class);
    }

    /**
     * 真正处理参数分解的地方，返回值作为方法上的参数
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //TODO zhb
        return null;
    }
}
