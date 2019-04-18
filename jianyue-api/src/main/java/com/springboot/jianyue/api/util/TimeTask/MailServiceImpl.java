package com.springboot.jianyue.api.util.TimeTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service("mailService2")
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String to, String subject, String content) throws MessagingException{
        //复杂邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);

        messageHelper.setFrom("303883148@qq.com");//发起者
        messageHelper.setTo(to);//接受者
        messageHelper.setSubject(subject);
        messageHelper.setText(content);
        messageHelper.addAttachment("timg.jpg", new File("D:\\timg.jpg"));
        mailSender.send(mimeMessage);
        try {
            mailSender.send(mimeMessage);
            System.out.println("发送简单邮件");
        }catch (Exception e){
            System.out.println("发送简单邮件失败");
        }
    }


}