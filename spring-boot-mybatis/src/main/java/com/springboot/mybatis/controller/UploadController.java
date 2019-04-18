package com.springboot.mybatis.controller;

import com.springboot.mybatis.uitl.UUIDUtil;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

//这是一个上传文件控制器
@RestController
public class UploadController {
    //指定一个临时路径作为上传目录
    //private static String UPLOAD_FOLDER = "C:\\Users\\Desktop\\UPLOAD\\";

    //遇到http://localhost:8080，则跳转至upload.html页面
    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("upload")
    public String fileUpload(@RequestParam("file") MultipartFile srcFile, RedirectAttributes redirectAttributes) {
        String returnFileName = "";
        //前端没有选择文件，srcFile为空
        if (srcFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "请选择一个文件");
            return "redirect:upload_status";
        }
        //选择了文件，开始上传操作
        try {
            //构建上传目标路径，找到了项目的target的classes目录
            File destFile = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!destFile.exists()) {
                destFile = new File("");
            }
            //输出目标文件的绝对路径
            System.out.println("file path:" + destFile.getAbsolutePath());
            //拼接子路径，以时间日期创建文件夹
            Date date = new Date();
            String dateFile = new SimpleDateFormat("yyyyMMdd/").format(date);
            File upload = new File(destFile.getAbsolutePath(), "upload/" + dateFile);
            //若目标文件夹不存在，则创建
            if (!upload.exists()) {
                upload.mkdirs();
            }
            //使用uuid重命名文件
            String fileName = srcFile.getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            System.out.println("完整的上传路径：" + dateFile + upload.getAbsolutePath() + "/" + fileExtension);
            //根据srcFile大小，准备一个字节数组
            byte[] bytes = srcFile.getBytes();
            //拼接上传路径
            //Path path = Paths.get(UPLOAD_FOLDER + srcFile.getOriginalFilename());
            //通过项目路径，拼接上传路径
            Path path = Paths.get(upload.getAbsolutePath() + "/" + UUIDUtil.getUUID32() + fileExtension);
            //** 开始将源文件写入目标地址
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message", "文件上传成功" + UUIDUtil.getUUID32() + fileExtension);
            returnFileName = "http://loaclhost:8081/upload/" + dateFile + UUIDUtil.getUUID32() + fileExtension;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnFileName;
    }

    //匹配upload_status页面
    @GetMapping("/upload_status")
    public String uploadStatusPage() {
        return "upload_status";
    }
}
