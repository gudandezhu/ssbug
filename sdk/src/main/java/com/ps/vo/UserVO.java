package com.ps.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {

    private int id;          //id
    private String name;     //名字
    private String account;  //账号


}
