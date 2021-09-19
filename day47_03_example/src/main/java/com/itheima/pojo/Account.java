package com.itheima.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 包名:com.itheima.pojo
 *
 * @author Leevi
 * 日期2020-11-14  15:24
 */
@Data
public class Account implements Serializable {
    private Integer id;
    private String name;
    private Double money;
}
