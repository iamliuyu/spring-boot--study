package com.springboot.jianyue.api.controller;

import com.springboot.jianyue.api.service.MailService;
import com.springboot.jianyue.api.util.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@RestController
@RequestMapping(value = "/api/mail")
public class MailController {
    @Resource
    private MailService mailService;

    @PostMapping("/simplemail")
    public ResponseResult sendSimpleEMail(@RequestParam("to") String to,
                                          @RequestParam("subject") String subject,
                                          @RequestParam("content") String content) {
        mailService.sendSimpleMail(to, subject, content);
        return ResponseResult.success();
    }

    @PostMapping("/mimemail")
    public ResponseResult sendMimeEMail(@RequestParam("to") String to,
                                        @RequestParam("subject") String subject,
                                        @RequestParam("content") String content,
                                        @RequestParam("file") MultipartFile file) throws Exception {
        File tempFile = File.createTempFile(file.getOriginalFilename(), file.getOriginalFilename());
        // MultipartFile to File
        file.transferTo(tempFile);
        mailService.sendMimeMail(to, subject, content, tempFile);
        return ResponseResult.success();
    }

    @PostMapping("/inlinemail")
    public ResponseResult sendInlineEMail(@RequestParam("to") String to,
                                        @RequestParam("subject") String subject,
                                        @RequestParam("content") String content) throws Exception {
        mailService.sendInlineMail(to, subject, content);
        return ResponseResult.success();
    }
}
