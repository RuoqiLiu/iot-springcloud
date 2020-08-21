package com.lrq.controller;

import com.alibaba.fastjson.JSONObject;
import com.lrq.feign_service.EmailClient;
import com.lrq.model.User;
import com.lrq.service.UserService;
import com.lrq.util.VerCodeGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private EmailClient emailClient;

    @RequestMapping("sendEmail")
    public String get(@RequestParam("emailAddress") String emailAddress) {
        String res= emailClient.sendEmail(emailAddress,"028");
        return res;
    }

    @RequestMapping("hello")
    public String sayHello(){
        return "helloRoki";
    }


    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String getLogin(HttpServletRequest req) throws IOException {
        //前台传过来的是json字符串，所以要用流的形式接收


        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuffer sb=new StringBuffer();
        String s=null;


        while((s=br.readLine())!=null){
            sb.append(s);
        }
        JSONObject jsonObject = JSONObject.parseObject(sb.toString());
        String username = jsonObject.getString("username");
        String password= jsonObject.getString("password");
//        System.out.println("username:"+username+" password:"+password);


        //User user1 = new User("lrq","1234","17856956456","850373535@qq.com", "sfsxxwalhvf",1,0,1,null,null,null);

        User user1=userService.selectByName(username);


        int code;//状态码
        String message;

        if(user1==null){
            code=404;
            message="用户不存在或未激活";

        }else{

            if(username.equals(user1.getName())&&password.equals(user1.getPassword())){
                code=200;
                message="登陆成功";
            }else{
                code=404;
                message="密码输入错误";

            }

        }

        Map< String , Object > jsonMap = new HashMap< String , Object>();
        jsonMap.put("msg",message);
        jsonMap.put("code",code);
        jsonMap.put("user",user1);

        String str = JSONObject.toJSONString(jsonMap);

        System.out.println(str);

        return str;
    }


    @RequestMapping("register")
    public String getRegister(HttpServletRequest req) throws IOException {
        //前台传过来的是json字符串，所以要用流的形式接收


        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuffer sb=new StringBuffer();
        String s=null;

        while((s=br.readLine())!=null){
            sb.append(s);
        }
        JSONObject jsonObject = JSONObject.parseObject(sb.toString());
        String username = jsonObject.getString("username");
        String mailbox= jsonObject.getString("mailbox");
        String password= jsonObject.getString("password");


        Date day = new Date();
        String user_key= VerCodeGenerateUtil.generateUserKey();
        String verCode =VerCodeGenerateUtil.generateVerCode();

        User user1 = new User(username,password,null,mailbox, user_key,1,0,1,day,verCode,null);

        userService.insert(user1);

        String res= emailClient.sendEmail(mailbox,verCode);

        int code;//状态码

        if(res.equals("发送成功")) {
            code=200;
        }else{
            code=404;
        }
        Map< String , Object > jsonMap = new HashMap< String , Object>();

        jsonMap.put("code",code);
        String str = JSONObject.toJSONString(jsonMap);
        //System.out.println(str);
        return str;
    }

    //校验激活码
    @RequestMapping(value = "/checkCode")
    public String checkCode(String code) {
        User user = userService.selectByCode(code);
        //System.out.println("搜索到的user"+user.toString());
        //如果用户不等于null，把用户状态修改status=1
        if (user != null) {
            user.setStatus(1);
            //把code验证码清空，已经不需要了
            user.setValidateCode("");

            //设置激活时间为当前时间
            Date day = new Date();
            user.setValidateTime(day);

            userService.update(user);

            return "激活成功    "+"<a href=\"http://localhost:8080"+"\">点击去首页登陆"+"</a>";
        }
        return "该用户不存在";
    }




}
