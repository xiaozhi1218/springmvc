package com.itheima.pojo;

import lombok.Data;

/**
 * 包名:com.itheima.pojo
 *
 * @author Leevi
 * 日期2020-11-14  14:37
 */
@Data
public class Course {
    private Integer id;
    private String name;
    private Boolean isShow;
    private Integer userId;
}
