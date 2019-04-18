package com.springboot.jianyue.api.util.TimeTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Async

public class TaskService {
    @Autowired
    private MailService mailService;

//    @Scheduled(cron = "*/100 * * * *  ?")
//    public void proces() throws MessagingException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        mailService.sendMail("16422802@qq.com","hello","这是刘宇在"+sdf.format(new Date())+"发的定时任务");
//    }
}


