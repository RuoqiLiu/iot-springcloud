package com.lrq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    JavaMailSenderImpl mailSender;


    @Value("${spring.mail.username}")
    private String sender;

    /**
     * 邮件发送
     * @param receiver 收件人
     * @param verCode 验证码
     * @throws MailSendException 邮件发送错误
     */

    @Async
    public void sendEmailVerCode(String receiver, String verCode) throws MailSendException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        //把激活链接加入里面,从网关中获取
        String content = "<a href=\"http://localhost:9527/server/user-service/checkCode?code="+verCode+"\">激活请点击这里"+verCode+"</a>";
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject("来自wcsn物联网平台的邮件");
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setText(content, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}

