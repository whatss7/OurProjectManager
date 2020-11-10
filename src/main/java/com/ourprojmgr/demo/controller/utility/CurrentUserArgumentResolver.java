package com.ourprojmgr.demo.controller.utility;

import com.ourprojmgr.demo.dbmodel.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 参数分解器，将请求头中的 token 转化为 User 对象
 * @author 朱华彬
 */
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
    /**
     * 判断是否处理该参数分解
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    /**
     * 真正处理参数分解的地方，返回值作为方法上的参数
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return webRequest.getAttribute("currentUser", RequestAttributes.SCOPE_REQUEST);
    }
}
