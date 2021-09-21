package com.itheima.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 包名:com.itheima.controller
 * @author Leevi
 * 日期2020-11-15  10:13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/sayHello")
    public String sayHello(){
        System.out.println("访问到了sayHello方法....");
        int num = 10/0;
        return "success";
    }
}
