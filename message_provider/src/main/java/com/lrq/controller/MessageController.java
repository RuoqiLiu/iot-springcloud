package com.lrq.controller;

import com.lrq.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessageController {

    @Autowired
    EmailService emailService;

    @RequestMapping("/email")
    public String sendEmail(@RequestParam("emailAddress") String emailAddress, @RequestParam("code") String code) {
        try {
            emailService.sendEmailVerCode(emailAddress,code);
            return "发送成功";
        } catch (Exception e) {
            return "发送失败";
        }
    }

}
