package com.itheima.dao;

import com.itheima.pojo.Account;

import java.util.List;

/**
 * 包名:com.itheima.dao
 *
 * @author Leevi
 * 日期2020-11-14  15:29
 */
public interface AccountDao {
    void addAccount(Account account);

    List<Account> findAll();

    void deleteById(int id);
}
