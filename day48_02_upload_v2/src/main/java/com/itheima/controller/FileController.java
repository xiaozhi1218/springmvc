package com.itheima.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 包名:com.itheima.controller
 * @author Leevi
 * 日期2020-11-15  08:58
 * 跨服务器文件上传：
 * 1. 使用springmvc的方式获取客户端上传的文件
 * 2. 使用跨服务器上传文件的API，将图片上传到文件服务器
 */
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 改成跨服务器文件上传
     * @param upload
     * @param pdesc
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    public String upload(MultipartFile upload, String pdesc) throws IOException {
        System.out.println(pdesc);
        try {
            //获取文件名
            String originalFilename = upload.getOriginalFilename();
            //上传文件的路径
            String uploadPath = "http://localhost:8899/upload/"+originalFilename;

            //创建一个文件上传的客户端
            Client client = Client.create();
            //建立与服务器的连接
            WebResource resource = client.resource(uploadPath);
            //将文件上传给服务器
            resource.put(upload.getBytes());
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
