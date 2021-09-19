package com.itheima.controller;

import com.itheima.pojo.Account;
import com.itheima.pojo.Result;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 包名:com.itheima.controller
 *
 * @author Leevi
 * 日期2020-11-14  15:25
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/addAccount")
    public Result addAccount(@RequestBody Account account){
        try {
            //1. 调用业务层的方法保存账户信息
            accountService.addAccount(account);
            return new Result(true,null,"保存账户信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,null,"保存账户信息失败");
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<Account> accountList = accountService.findAll();
            return new Result(true,accountList,"查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,null,"查询成功");
        }

    }

    @RequestMapping("/{id}")
    public Result delete(@PathVariable("id") int id){
        try {
            //调用业务层的方法根据id删除账户
            accountService.deleteById(id);
            return new Result(true,null,"删除账户信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,null,"删除账户信息失败");
        }
    }
}
