package com.ourprojmgr.demo;

import com.ourprojmgr.demo.controller.utility.LoginInterceptor;
import com.ourprojmgr.demo.controller.utility.CurrentUserArgumentResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@MapperScan("com.ourprojmgr.demo.dao")
public class AppConfig implements WebMvcConfigurer {
    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/**");
    }

    /**
     * 配置参数分解器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserArgumentResolver());
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public CurrentUserArgumentResolver currentUserArgumentResolver() {
        return new CurrentUserArgumentResolver();
    }

    @Bean
    public CorsFilter corsFilter() {
        var config = new CorsConfiguration();
        //开放哪些ip、端口、域名的访问权限，星号表示开放所有域
        config.addAllowedOrigin("*");
        //是否允许发送Cookie信息
        config.setAllowCredentials(true);
        //开放哪些 Http 方法
        for (var method : new HttpMethod[]{HttpMethod.GET, HttpMethod.POST,
                HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE}) {
            config.addAllowedMethod(method);
        }
        //允许HTTP请求中的携带哪些Header信息
        config.addAllowedHeader("*");
        //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
        var configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }
}
