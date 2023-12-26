/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.config;

import booklibrary.library_backend.entity.RestBean;
import booklibrary.library_backend.entity.database_obj.Account;
import booklibrary.library_backend.entity.view_obj.response.AuthorizeViewObj;
import booklibrary.library_backend.filter.JwtAuthorizeFilter;
import booklibrary.library_backend.service.AccountService;
import booklibrary.library_backend.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description: SpringSecurity配置类
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
@Configuration
public class SecurityConfiguration {

    @Resource
    JwtUtils jwtUtils;
    @Resource
    JwtAuthorizeFilter jwtAuthorizeFilter;
    @Resource
    AccountService accountService;

    /**
     * @Description: Security过滤链配置
     * @Param: [http]
     * @return: org.springframework.security.web.SecurityFilterChain
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(conf -> conf
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )//拦截器配置
                .formLogin(conf -> conf
                        .loginProcessingUrl("/api/auth/login")
                        .successHandler(this::onAuthenticationSuccess)
                        .failureHandler(this::onAuthenticationFailure)
                )//登陆参数
                .logout(conf -> conf
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(this::onLogoutSuccess)
                )//登出参数
                .exceptionHandling(conf -> conf
                        .authenticationEntryPoint(this::onUnauthorized)
                        .accessDeniedHandler(this::onAccessDeny)
                )//异常返回
                .csrf(AbstractHttpConfigurer::disable)//CSRF配置（false）
                .sessionManagement(conf -> conf
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )//session配置（false）
                .addFilterBefore(jwtAuthorizeFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * @Description: 登陆成功
     * @Param: [request, response, authentication]
     * @return: null
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        User user = (User) authentication.getPrincipal();
        Account account = accountService.findAccountByNameOrEmail(user.getUsername());
        String Token = jwtUtils.CreateJwt(user, account.getId(), account.getUsername());
        AuthorizeViewObj avo = account.asViewObj(AuthorizeViewObj.class, v -> {
            v.setToken(Token);
            v.setExpire(jwtUtils.expireTime());
        });
        response.getWriter().write(RestBean.success(avo).ToJsonString());
    }

    /**
     * @Description: 登出成功
     * @Param: [request, response, authentication]
     * @return: null
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        String authorization = request.getHeader("Authorization");
        if (jwtUtils.inValidateJwt(authorization))
            writer.write(RestBean.success("退出登录成功").ToJsonString());
        else {
            writer.write(RestBean.failure(400, "退出登录失败").ToJsonString());
            response.setStatus(400);
        }
    }

    /**
     * @Description: 登录失败
     * @Param: [request, response, exception]
     * @return: null
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(RestBean.unauthorized(exception.getMessage()).ToJsonString());
    }

    /**
     * @Description: 登出失败
     * @Param: [request, response, authException]
     * @return: null
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    public void onUnauthorized(HttpServletRequest request,
                               HttpServletResponse response,
                               AuthenticationException authException) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(RestBean.unauthorized(authException.getMessage()).ToJsonString());
    }

    /**
     * @Description: 拒接访问返回
     * @Param: [request, response, accessDeniedException]
     * @return: 向response中写入RestBean<String>
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    public void onAccessDeny(HttpServletRequest request,
                             HttpServletResponse response,
                             AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(RestBean.forbidden(accessDeniedException.getMessage()).ToJsonString());
    }
}
