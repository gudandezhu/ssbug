package com.ps.usercenter.service;

import com.ps.vo.RegisterVO;

public interface RegisterService {

    int register(RegisterVO register);



    String sendPhoneCode(String phone);

}
