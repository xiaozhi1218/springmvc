package com.itheima.mm.service.impl;

import com.itheima.mm.dao.CatalogDao;
import com.itheima.mm.dao.CourseDao;
import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.TagDao;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
/**
 * 包名:com.itheima.mm.service
 *
 * @author Leevi
 * 日期2020-11-02  10:51
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CatalogDao catalogDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private QuestionDao questionDao;
    @Override
    public void addCourse(Course course) throws Exception {
        courseDao.add(course);
    }

    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) throws Exception {
        //1. 要分清楚客户端传入的数字到底是int还是String类型
        //2. mybatis框架在进行判断的时候，会将int类型的0当做null处理，所以我们要将请求参数中int类型的0转成String类型的0
        //先出QueryPageBean中的查询条件中的0和1
        Map map = queryPageBean.getQueryParams();
        if (map != null) {
            //判断status是否是空字符串
            if (!map.get("status").equals("")) {
                String status = (Integer) map.get("status") + "";
                map.put("status",status);
            }
        }
        //1. 查询总条数
        Long total = courseDao.findTotalCourse(queryPageBean);
        //2. 查询当前页数据集合
        List<Course> courseList = courseDao.findCourseListByPage(queryPageBean);
        return new PageResult(total,courseList);
    }

    @Override
    public void updateCourse(Course course) throws Exception {
        courseDao.update(course);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            //删除之前要先进行判断
            //1. 判断当前学科是否有关联的二级目录: 以当前学科的id到t_catalog表中查询二级目录的数量，如果数量为0表示没有关联的二级目录
            Long catalogCount = catalogDao.findCatalogCountByCourseId(id);
            if (catalogCount != 0) {
                //有关联的二级目录，不能删除
                throw new RuntimeException("有关联的二级目录，不能删除");
            }

            //2. 判断当前学科是否有关联的标签
            Long tagCount = tagDao.findTagCountByCourseId(id);
            if (tagCount != 0) {
                //有关联的标签，不能删除
                throw new RuntimeException("有关联的标签，不能删除");
            }

            //3. 判断当前学科是否有关联的题目
            Long questionCount = questionDao.findQuestionCountByCourseId(id);
            if (questionCount != 0) {
                //有关联的题目，不能删除
                throw new RuntimeException("有关联的题目，不能删除");
            }

            //可以删除，调用CourseDao的方法进行删除
            courseDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Course> findAll(Map parameterMap) throws Exception {
        if (parameterMap.get("status") != null) {
            String status = (int)parameterMap.get("status") +"";
            parameterMap.put("status",status);
        }
        List<Course> courseList = courseDao.findAll(parameterMap);
        return courseList;
    }
}
