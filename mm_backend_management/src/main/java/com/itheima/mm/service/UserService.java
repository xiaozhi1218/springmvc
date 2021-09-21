package com.itheima.mm.service;

import com.itheima.mm.pojo.User;

/**
 * 包名:com.itheima.mm.service
 *
 * @author Leevi
 * 日期2020-11-15  11:51
 */
public interface UserService {
    User findUser(User parameterUser) throws Exception;
}
