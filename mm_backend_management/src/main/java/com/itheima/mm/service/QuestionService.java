package com.itheima.mm.service;

import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Question;

/**
 * 包名:com.itheima.mm.service
 *
 * @author Leevi
 * 日期2020-11-15  11:51
 */
public interface QuestionService {
    PageResult findBasicQuestionListByPage(QueryPageBean queryPageBean) throws Exception;

    void add(Question question);
}
