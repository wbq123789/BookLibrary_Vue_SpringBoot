/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.filter;

import booklibrary.library_backend.utils.Const;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
* @Description: CORS（跨域访问）配置
* @Author: 王贝强
* @Date: 2023/12/26
*/
@Component
@Order(Const.ORDER_CORS)
public class CorsFilter extends HttpFilter {
    /**
    * @Description: 自定义过滤器
    * @Param: [request, response, chain]
    * @return: void
    * @Author: 王贝强
    * @Date: 2023/12/26
    */
    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        this.addCorsFilter(request,response);
        chain.doFilter(request,response);
    }
    private void addCorsFilter(HttpServletRequest request, HttpServletResponse response){
        //定义访问来源地址
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        //定义访问请求方法
        response.addHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS");
        //定义访问必须携带请求头参数
        response.addHeader("Access-Control-Allow-Headers","Authorization,Content-Type");
    }
}
