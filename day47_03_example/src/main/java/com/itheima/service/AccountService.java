package com.itheima.service;

import com.itheima.pojo.Account;

import java.util.List;

/**
 * 包名:com.itheima.service
 *
 * @author Leevi
 * 日期2020-11-14  15:27
 */
public interface AccountService {
    void addAccount(Account account);

    List<Account> findAll();


    void deleteById(int id);
}
