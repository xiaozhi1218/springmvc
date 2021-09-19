package com.itheima.controller;

import com.itheima.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 包名:com.itheima.controller
 * @author Leevi
 * 日期2020-11-14  09:46
 * 一、获取"name=value&name=value"类型的请求参数:
 *    1. 一个一个参数进行获取: 在处理请求的方法中，添加和请求参数同名的参数即可
 *       如果请求参数名和方法的参数名不一致，我们该怎么获取？我们可以通过RequestParam注解，指定获取的请求参数名
 *    2. 封装请求参数:
 *       1. POJO类型: 在方法中加入一个POJO类型的参数，要求POJO中的属性和请求参数名一致，POJO的属性一定要有set方法,
 *          POJO类一定要有无参构造
 *       2. Map 类型: 在方法中加入一个Map类型的参数，此时map中接收的参数的key就是参数名，value就是参数值
 *          一定要在Map类型的参数前添加RequestParam注解
 *
 *       3. 如果是多个同名参数，我们可以使用List或者数组进行封装，比如说"批量删除"
 *          1. 添加RequestParam注解
 *          2. 方法的参数名要和接收的请求参数名一致
 *
 *       4. 如果接收的请求参数是: 既有一个name对应一个value，也有一个name对应多个value
 *          比如说注册的时候: username、password、nickname、birthday、hobbies
 *          那么我们就将请求参数封装到POJO中，一个参数名对应一个参数值的情况我们就使用简单类型
 *          一个参数名对应多个参数值的情况我们就使用List类型
 *  二、获取请求参数当中存在的问题:
 *     1. post请求方式提交参数时候的中文乱码问题: 使用过滤器(使用spring提供的过滤器), 我们需要做的就是在web.xml中配置过滤器
 *        1. 在web.xml中配置过滤器的拦截路径
 *        2. 在web.xml中通过过滤器的初始化参数配置，告诉过滤器我们想要使用什么编码
 *
 *     2. Date类型转换的问题
 *        spring中已经内置了很多的类型转换器，比如将String转成数字、将String转成Array、将String转成Date
 *        如果有一些转换器是spring里面没有的，但是我们需要使用的，那么就需要自定义(自己指定)类型转换规则
 *        例如: 将请求参数中 yyyy-MM-dd 类型的字符串，转换成Date, 实现方式有两种
 *             1. 在要进行类型转换的属性上使用DateTimeFormat注解实现局部类型转换
 *             2. 使用自定义类型转换器的方式，实现全局的类型转换
 *                2.1 编写一个类型转换器，实现Converter接口
 *                2.2 重写convert在这里面实现类型转换
 *                2.3 一定要在spring的配置文件中，配置类型转换器(目的就是让spring使用你的类型转换器)
 *
 * 三、获取json类型的请求参数:
 *     1. 只能采用封装的形式，要么封装到POJO中，要么封装到Map中
 *     2. 要在方法的参数前添加RequestBody注解
 *     3. 引入json转换的依赖(springmvc默认支持的是jackson)
 */
@Controller
@RequestMapping("/user")
@SessionAttributes(names = {"uname","age"})
public class UserController {
    @RequestMapping("/findByName")
    public String findByName(@RequestParam("username") String name, int age){
        System.out.println("根据name为"+name+",查询用户，年龄为:"+age);
        return "success";
    }

    @RequestMapping("/register")
    public String register(User user){
        System.out.println("获取到的请求参数：" + user);
        return "success";
    }

    /*@RequestMapping("/register")
    public String register(@RequestParam Map map){
        System.out.println("获取到的请求参数：" + map);
        return "success";
    }*/

    @RequestMapping("/deleteMore")
    public String deleteMore(@RequestParam List<Integer> ids){
        System.out.println("要删除的数据:" + ids);
        return "success";
    }

    @RequestMapping("/useBody")
    public String useRequestBody(@RequestBody String body){
        //目标: 使用body参数来获取请求体的内容----->从请求体中获取参数
        System.out.println("请求体的内容是:" + body);
        return "success";
    }

    @RequestMapping("/useJson")
    public String useJson(@RequestBody User user){
        //目标: 使用body参数来获取请求体的内容----->从请求体中获取参数
        System.out.println("请求体的内容是:" + user);
        return "success";
    }

    /**
     *  .../findCheckItem?mealId=1&groupId=3&itemId=10
     * 目标: /套餐id/检查组的id/检查项的id ----> .../1/3/10
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,path = "/{mealId}/{groupId}/{itemId}")
    public String findCheckItem(@PathVariable("mealId") int mealId,@PathVariable("groupId") int groupId,@PathVariable("itemId") int itemId){
        System.out.println("根据id查询:mealId=" + mealId + ",groupId=" + groupId + ",itemId=" + itemId);
        return "success";
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "/{mealId}/{groupId}/{itemId}")
    public String deleteCheckItem(@PathVariable("mealId") int mealId,@PathVariable("groupId") int groupId,@PathVariable("itemId") int itemId){
        System.out.println("根据id删除:mealId=" + mealId + ",groupId=" + groupId + ",itemId=" + itemId);

        return "success";
    }

    @RequestMapping("/setSessionValue")
    public String setSessionValue(Model model){
        //model是springmvc中的数据模型，其实就是存放数据的对象，数据是存放在request域
        model.addAttribute("uname","奥巴马");
        model.addAttribute("age",19);
        model.addAttribute("address","usa");
        return "success";
    }


    @RequestMapping("/responseJson")
    @ResponseBody
    public User responseJson(){
        //模拟从数据库查询数据
        User user = new User();
        user.setUsername("周杰伦");
        user.setPassword("123456");
        user.setNickname("双节棍");
        user.setBirthday(new Date());
        List<String> hobbiesList = new ArrayList<>();
        hobbiesList.add("basketball");
        hobbiesList.add("football");
        user.setHobbies(hobbiesList);
        //将user转换成json响应给客户端
        return user;
    }
}
