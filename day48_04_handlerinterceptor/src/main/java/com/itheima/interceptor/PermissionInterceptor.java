package com.itheima.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 包名:com.itheima.interceptor
 * @author Leevi
 * 日期2020-11-15  10:47
 * 拦截器:
 *    作用: 在执行controller的方法之前做预处理，以及执行controller的方法之后做后处理
 *    步骤:
 *       1. 写一个类实现HandlerInterceptor接口
 *       2. 选择实现其中的方法:
 *          1. preHandle() 在处理器方法之前执行
 *          2. postHandle() 在处理器方法之后、视图渲染之前执行
 *          3. afterCompletion() 在视图渲染之后执行，官方建议可以在该方法中做一些资源清理操作
 *       3. 在springmvc配置文件中进行拦截器的配置(配置拦截器的拦截路径)
 */
public class PermissionInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("PermissionInterceptor的preHandle方法执行了...");
        //返回值为true就是放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("PermissionInterceptor的postHandle方法执行了...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("PermissionInterceptor的afterCompletion方法执行了...");
    }
}
