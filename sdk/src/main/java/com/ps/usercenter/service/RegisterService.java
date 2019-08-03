package com.ps.usercenter.service;

import com.ps.vo.RegisterVO;
import com.ps.vo.UserVO;

import javax.servlet.http.HttpSession;

public interface RegisterService {

    int register(RegisterVO register, HttpSession session);



    boolean sendPhoneCode(String phone, HttpSession session);

}
