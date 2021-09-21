package com.itheima.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 包名:com.itheima.controller
 *
 * @author Leevi
 * 日期2020-11-15  10:45
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/sayHello")
    public String sayHello(){
        System.out.println("sayHello方法被调用了...");
        return "success";
    }
}
