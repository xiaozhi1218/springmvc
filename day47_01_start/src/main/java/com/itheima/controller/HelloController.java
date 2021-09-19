package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 包名:com.itheima.controller
 * @author Leevi
 * 日期2020-11-14  08:36
 * 1. springmvc的配置Controller中的方法接收请求
 *    1. IOC配置，所有的Controller对象配置到spring的核心容器中
 *    2. 进行映射路径的配置，在要处理请求的方法上添加RequestMapping注解来配置映射路径
 *    3. 在配置文件中，配置DispatcherServlet的映射路径，并且配置服务器启动时加载
 *    4. 告诉DispatcherServlet,要加载的spring/springmvc的配置文件的路径
 *       通过servlet的初始化参数
 *
 * 2. 配置跳转到成功页面(了解)
 *    1. 配置视图解析器(在spring的配置文件中),将Controller的方法的返回值解析成真正的视图地址
 *    2. 让Controller的方法，返回一个匹配视图解析器的字符串
 */
@Controller
public class HelloController {
    @RequestMapping("/hello/sayHello")
    public String sayHello(){
        System.out.println("hello world...");
        //跳转到成功页面: /WEB-INF/pages/success.jsp
        return "success";
    }
}
