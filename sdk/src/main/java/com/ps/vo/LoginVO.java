package com.ps.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVO implements Serializable {

    String account;  //账号
    String password; //密码
    String phone;    //手机号
//    String verifyCode;//验证码

}