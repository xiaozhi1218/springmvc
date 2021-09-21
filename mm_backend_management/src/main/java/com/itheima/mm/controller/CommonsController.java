package com.itheima.mm.controller;
import com.itheima.mm.entity.Result;
import com.itheima.mm.utils.UploadUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 包名:com.itheima.mm.controller
 *
 * @author Leevi
 * 日期2020-11-04  14:41
 */
@RestController
@RequestMapping("/commons")
public class CommonsController {
    @RequestMapping("/uploadFile")
    public Result uploadFile(MultipartFile upload, HttpSession session) throws IOException {
        FileOutputStream os = null;
        //1. 获取客户端上传的文件: 其实就是获取一个字节输入流
        InputStream is = null;
        try {
            //图片存储的路径
            String imgUrl = null;
            //获取客户端上传文件的那个文件名字
            String fileName = upload.getOriginalFilename();
            //解决图片重名问题: 使用UUID作为图片的名字
            fileName = UploadUtils.getUUIDName(fileName);
            //一个目录中不能存放过多文件: 我们选择创建多级目录
            String randomDir = UploadUtils.getDir();
            //第二步: 准备一个文件夹，用于存储客户端上传的文件
            String realPath = session.getServletContext().getRealPath("img/upload" + randomDir);
            File file = new File(realPath);

            //如果硬盘中并没有该文件夹，则将其在硬盘中创建出来
            if (!file.exists()) {
                file.mkdirs();
            }

            //第三步: 使用字节输出流将客户端上传的文件输出到指定文件夹中
            os = new FileOutputStream(new File(file, fileName));

            imgUrl = "img/upload" + randomDir + "/" + fileName;

            is = upload.getInputStream();
            //循环读写，将is中的字节通过os写入到磁盘
            IOUtils.copy(is, os);
            return new Result(true, "上传图片成功", imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "上传图片失败");
        } finally {
            os.close();
            is.close();
        }
    }
}
