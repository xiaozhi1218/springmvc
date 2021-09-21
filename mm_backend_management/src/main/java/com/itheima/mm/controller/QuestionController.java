package com.itheima.mm.controller;

import com.itheima.mm.constants.Constants;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.QuestionService;
import com.itheima.mm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 包名:com.itheima.mm.controller
 *
 * @author Leevi
 * 日期2020-11-04  09:20
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping(value = "/basic")
    public Result findBasicListByPage(@RequestBody QueryPageBean queryPageBean) throws IOException {
        try {
            //调用业务层的方法查询分页数据
            PageResult pageResult = questionService.findBasicQuestionListByPage(queryPageBean);
            return new Result(true,"获取基础题库列表成功",pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"获取基础题库列表失败");
        }
    }
    @RequestMapping("/add")
    public Result add(@RequestBody Question question, HttpSession session) throws IOException {
        try {
            //我们需要自己手动设置一些数据:status题目的状态,reviewStatus题目的审核状态，createDate题目的创建时间,userId题目的录入人的id
            question.setStatus(0);
            question.setReviewStatus(0);
            question.setCreateDate(DateUtils.parseDate2String(new Date()));
            User user = (User) session.getAttribute(Constants.USER_SESSION_KEY);
            question.setUserId(user.getId());
            //2. 调用业务层的方法，存储试题的信息
            questionService.add(question);
            return new Result(true,"新增试题成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"新增试题失败");
        }
    }
}
