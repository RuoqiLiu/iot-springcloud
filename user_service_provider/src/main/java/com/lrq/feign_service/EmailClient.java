package com.lrq.feign_service;

import com.lrq.factory.EmailClientFactory;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//添加一个后备工厂，在失败时使用
@FeignClient(value = "MESSAGE-PROVIDER", fallbackFactory = EmailClientFactory.class)
public interface EmailClient {

    @RequestMapping("/email")
    public String sendEmail(@RequestParam("emailAddress") String emailAddress, @RequestParam("code") String code);

}