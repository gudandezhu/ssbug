package com.ps.user_center.service.impl;

import com.ps.user_center.mapper.RegisterMapper;
import com.ps.usercenter.service.RegisterService;
import com.ps.utils.PhoneCode;
import com.ps.vo.RegisterVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RegisterServiceImpl implements RegisterService {


    @Autowired
    private RegisterMapper mapper;

    @Override
    public int register(RegisterVO register) {

        //1.判断手机号或账号有无重复
        if (mapper.findUserByPhoneOrAccount(register.getPhone())!=null) {
            return 0;
        }

        return mapper.register(register);
    }

    @Override
    public String sendPhoneCode(String phone) {


        String send = PhoneCode.send(phone);

        return send;
    }

}
