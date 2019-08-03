package com.ps.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageVO<T>  implements Serializable {

    private int code;   //状态码
    private String msg; //信息
    private T data;     //内容

    public MessageVO() {
        this.code=200;
        this.msg="ok";
    }
}
