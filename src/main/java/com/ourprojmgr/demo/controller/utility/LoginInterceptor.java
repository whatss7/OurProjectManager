package com.ourprojmgr.demo.controller.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.exception.BusinessErrorType;
import com.ourprojmgr.demo.jsonmodel.ApiResponseJson;
import com.ourprojmgr.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 拦截器，用于检查用户是否登录
 *
 * @author 朱华彬
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    /**
     * 前处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        //如果不是映射到控制器方法，则直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //若该方法上带有 @LoginRequired 注解，则检查 token 是否有效
        //若无该注解，则直接放行
        if (handlerMethod.hasMethodAnnotation(LoginRequired.class)) {
            String token = getTokenFromAuthorization(request.getHeader("Authorization"));
            //System.out.println(token);
            if (token == null) {
                //无 token
                ApiResponseJson apiResponse = new ApiResponseJson(
                        BusinessErrorType.NOT_LOGIN.getType(),
                        "There is no token.");
                setApiResponse(response, BusinessErrorType.NOT_LOGIN.getStatus(), apiResponse);
                return false;
            }
            User user = getUserByToken(token);
            if (user == null) {
                //token 非法或用户不存在
                ApiResponseJson apiResponse = new ApiResponseJson(
                        BusinessErrorType.NOT_LOGIN.getType(),
                        "Invalid token or user not exist");
                setApiResponse(response, BusinessErrorType.NOT_LOGIN.getStatus(), apiResponse);
                return false;
            }
            //将获取的 User 存到 HttpServletRequest 的 Attribute 中，
            //以供后面的参数分解器使用
            request.setAttribute("currentUser", user);
            return true;
        }
        return true;
    }

    /**
     * 从请求头的 Authorization 字段获取 token
     *
     * @param authorization Authorization 字段
     * @return token，若无 Authorization 字段则返回 null
     */
    private String getTokenFromAuthorization(@Nullable String authorization) {
        if (authorization == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("Bearer\\s+(.+)");
        Matcher matcher = pattern.matcher(authorization);
        return matcher.find() ? matcher.group(1) : null;
    }

    /**
     * 用 token 获取 User 对象。
     *
     * @param token JSON Web Token
     * @return 若 token 非法或用户不存在，则返回 null
     */
    private User getUserByToken(String token) {
        String userIdString;
        try {
            userIdString = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            //JWT 解析失败
            return null;
        }
        int userId;
        try {
            userId = Integer.parseInt(userIdString);
        } catch (NumberFormatException e) {
            //不是整数
            return null;
        }
        return userService.getUserById(userId);
    }

    /**
     * 给响应设置 HTTP 响应码和 ApiResponseJson
     */
    private void setApiResponse(HttpServletResponse response,
                                HttpStatus status,
                                ApiResponseJson json) {
        response.setContentType("application/json");
        response.setStatus(status.value());
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.getWriter().write(mapper.writeValueAsString(json));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
