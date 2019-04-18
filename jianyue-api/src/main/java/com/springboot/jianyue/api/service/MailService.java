package com.springboot.jianyue.api.service;

import java.io.File;

public interface MailService {

    void sendSimpleMail(String to,String subject,String content);

    void sendMimeMail(String to, String subject, String content, File file) throws Exception;

    void sendInlineMail(String to, String subject, String content) throws Exception;
}
