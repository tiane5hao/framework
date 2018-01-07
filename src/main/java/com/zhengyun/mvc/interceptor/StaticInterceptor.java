package com.zhengyun.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 静态资源拦截器
 * Created by 听风 on 2017/12/30.
 */
public class StaticInterceptor implements Interceptor{
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getRequestURI().startsWith("/js")){
            response.sendRedirect("/");
        }

        return true;
    }

    public boolean postHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
}
