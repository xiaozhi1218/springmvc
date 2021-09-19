package com.itheima.controller;

import com.itheima.pojo.Course;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 包名:com.itheima.controller
 *
 * @author Leevi
 * 日期2020-11-14  14:38
 * 添加了ModelAttribute注解的方法，会在控制器方法之前执行
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @ModelAttribute
    public Course init(){
        //模拟: 从session中获取用户id，然后设置给course对象
        Course course = new Course();
        course.setUserId(13);
        return course;
    }

    @RequestMapping("/add")
    public String add(Course course){
        //手动从session中获取user，然后往course设置user的id
        System.out.println("添加学科信息:"+course);
        return "success";
    }

    @RequestMapping("/update")
    public String update(Course course){
        System.out.println("修改学科信息:"+course);
        return "success";
    }
}
