package com.itheima.handler;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 包名:com.itheima.handler
 * @author Leevi
 * 日期2020-11-15  10:17
 * 全局异常处理器：
 *     作用: 处理整个项目中所有的controller抛出的异常
 *     步骤: 1. 编写一个类实现HandlerExceptionResolver接口
 *          2. 重写resolveException方法
 *          3. 在springmvc的配置文件中，配置异常解析器
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //ex表示这次的异常信息,使用它可以收集异常信息
        ex.printStackTrace();//这句代码仅仅是在控制台打印文件

        //返回ModelAndView对象就必然要走视图解析器
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
