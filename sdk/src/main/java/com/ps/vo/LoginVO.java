package com.ps.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVO implements Serializable {

   private static final long serialVersionUID = 4537362103328201702L;

   private String account;  //账号
   private String password; //密码
   private String phone;    //手机号
//    String verifyCode;//验证码

}