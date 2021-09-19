package com.itheima.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 包名:com.itheima.pojo
 *
 * @author Leevi
 * 日期2020-11-14  10:12
 */
@Data
public class User implements Serializable {
    private String username;
    private String password;
    private String nickname;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private List<String> hobbies;
}
