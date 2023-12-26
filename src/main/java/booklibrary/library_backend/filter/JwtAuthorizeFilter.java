/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.filter;

import booklibrary.library_backend.utils.JwtUtils;
import booklibrary.library_backend.utils.UserUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/**
* @Description: 配置JWT权限校验器
* @Author: 王贝强
* @Date: 2023/12/26
*/
@Component
public class JwtAuthorizeFilter extends OncePerRequestFilter {
    @Resource
    JwtUtils jwtUtils;
    @Resource
    UserUtils userUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        //请求访问必须携带Authorization请求头参数，内容：bearer+" "+Token
        String authorization =request.getHeader("Authorization");
        DecodedJWT jwt=jwtUtils.resolveJwt(authorization);
        if(jwt!=null){
            UserDetails user=userUtils.toUser(jwt);
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            request.setAttribute("id",userUtils.toId(jwt));
        }
        filterChain.doFilter(request,response);
    }
}
