package com.springboot.jianyue.api.util.TimeTask;

import javax.mail.MessagingException;

public interface MailService {
    /**
     * 发送简单邮件
     */
    void sendMail(String to,String subject,String content) throws MessagingException;

}