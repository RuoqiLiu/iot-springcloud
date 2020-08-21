package com.lrq.factory;

import com.lrq.feign_service.EmailClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailClientFactory implements FallbackFactory<EmailClient> {

//调用失败的后备工厂
    public EmailClient create(Throwable cause) {
        return new EmailClient() {
            public String sendEmail(String emailAddress,String code){
                return "Feign调用失败";
            }
        };
    }

}
