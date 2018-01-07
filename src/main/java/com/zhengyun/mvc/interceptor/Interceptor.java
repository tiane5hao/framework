package com.zhengyun.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 听风 on 2017/12/30.
 */
public interface Interceptor {

    boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception;

    boolean postHandle(HttpServletRequest request,
                       HttpServletResponse response, Object handler) throws Exception;
}
