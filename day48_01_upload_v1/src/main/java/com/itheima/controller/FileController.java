package com.itheima.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 包名:com.itheima.controller
 * @author Leevi
 * 日期2020-11-15  08:58
 * 服务器端获取客户端上传的文件:
 * 1. 在springmvc的配置文件中配置文件解析器，将字节输入流解析成MultipartFile对象
 * 2. 在控制器方法中添加MultipartFile类型的参数
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @RequestMapping("/upload")
    public String upload(MultipartFile upload, String pdesc, HttpSession session) throws IOException {
        System.out.println(pdesc);
        InputStream is = null;
        FileOutputStream out = null;
        try {
            ServletContext servletContext = session.getServletContext();
            // 在部署的项目路径下准备一个upload目录
            String realPath = servletContext.getRealPath("upload");
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //获取文件名
            String originalFilename = upload.getOriginalFilename();
            // 使用文件输出流将客户端上传的文件输出到指定目录
            out = new FileOutputStream(new File(file, originalFilename));

            //客户端上传文件的输入流
            is = upload.getInputStream();

            IOUtils.copy(is, out);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        } finally {
            is.close();
            out.close();
        }
    }
}
