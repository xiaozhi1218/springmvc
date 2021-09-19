package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 包名:com.itheima.pojo
 *
 * @author Leevi
 * 日期2020-11-14  15:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private Boolean flag;
    private Object result;
    private String message;
}
