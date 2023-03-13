package com.fxj.recordmanager.config;

import com.fxj.recordmanager.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AuthFilter> registAuth(){
        FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new AuthFilter());//添加自己的过滤器
        filterFilterRegistrationBean.setName("token-Auth");
        filterFilterRegistrationBean.addUrlPatterns("/*");//拦截所有请求
        filterFilterRegistrationBean.setOrder(1);//优先执行，数越小，优先级越高
        return filterFilterRegistrationBean;
    }
}