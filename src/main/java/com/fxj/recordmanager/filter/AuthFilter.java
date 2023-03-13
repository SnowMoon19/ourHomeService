package com.fxj.recordmanager.filter;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson2.JSON;
import com.fxj.recordmanager.common.results.Result;
import com.fxj.recordmanager.common.results.ResultCodeEnum;
import com.fxj.recordmanager.utils.auth.JwtUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AuthFilter implements Filter {

    private JwtUtils jwtUtils = new JwtUtils();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String,String> map=new HashMap<>();
        Result result = null;
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        if (requestURI != null){
            // 请求放行
            if (StringUtils.equalsIgnoreCase("/user/login", requestURI)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            } else {
                String token = ((HttpServletRequest) servletRequest).getHeader("token");
                if (!StringUtils.isEmpty(token)){
                    //token验证结果
                    int verify  = JwtUtils.verify(token,(HttpServletRequest) servletRequest);
                    if(verify != 1){
                        //验证失败
                        if(verify == 2){
                            result = Result.build(ResultCodeEnum.TOKEN_EXPIRE);
                        }else if(verify == 0){
                            result = Result.build(ResultCodeEnum.USER_VERIFY_ERROR);
                        }
                    } else {
                        //验证成功，放行
                        filterChain.doFilter(servletRequest,servletResponse);
                        return;
                    }
                }else {
                    result = Result.build(ResultCodeEnum.TOKEN_NEEDED);
                }
            }
        }
        servletResponse.setContentType("application/json");
        servletResponse.setCharacterEncoding("utf-8");
        PrintWriter writer = servletResponse.getWriter();
        if (result != null) {
            //todo 这里jdk大于1.8版本会有警告
            writer.write(JSON.toJSON(result).toString());
            writer.flush();
            writer.close();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

