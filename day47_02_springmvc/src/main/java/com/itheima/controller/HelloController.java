package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 包名:com.itheima.controller
 * @author Leevi
 * 日期2020-11-14  08:36
 * 1. RequestMapping注解，用于配置请求的映射路径
 *    1. 使用位置: Controller类上，以及方法上
 *       1.1 类上的RequestMapping注解的值其实就是该模块(类)的标识，
 *           这里就要求每一个Controller类上的RequestMapping的值不同
 *       1.2 同一个类中的各个方法的RequestMapping的值其实就是该方法的标识，
 *           这里就要求同一个类的每个方法的RequestMapping的的值不同
 *    2. RequestMapping注解的属性
 *       2.1 path或者value 指定请求映射路径
 *       2.2 method 指定该方法支持的请求方式
 *           扩展: PostMapping注解就相当于RequestMapping注解指定method为POST
 *       2.3 其它不重要的属性: params指定请求参数的规则 和 headers指定请求头的规则
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    //@RequestMapping(value = "/sayHello",method = RequestMethod.POST)
    @PostMapping("/sayHello")
    public String sayHello(){
        System.out.println("hello world...");
        //跳转到成功页面: /WEB-INF/pages/success.jsp
        return "success";
    }
}
