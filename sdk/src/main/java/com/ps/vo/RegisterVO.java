package com.ps.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterVO implements Serializable {

    private String name;
    private String account;
    private String password;
    private String phone;
    private String verificationCode;

}
