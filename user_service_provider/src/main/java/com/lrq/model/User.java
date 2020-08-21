package com.lrq.model;

import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
//@NoArgsConstructor
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    private String name ;

    private String password ;

    private String phone ;

    private String email ;

    private String userKey;
    //用户类型
    private int type ;
    //当前状态,代表是否激活,未激活是0，激活了是1
    private int status ;
    private int sex ;
    //注册时间
    private Date registerTime ;
    //验证码
    private String validateCode ;

    //验证时间
    private Date validateTime ;

}
