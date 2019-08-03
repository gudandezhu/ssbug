package com.ps.user_center.service.impl;

import com.ps.user_center.mapper.RegisterMapper;
import com.ps.usercenter.service.RegisterService;
import com.ps.utils.PhoneCode;
import com.ps.vo.RegisterVO;
import com.ps.vo.UserVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper mapper;

    @Override
    public int register(RegisterVO register, HttpSession session) {
        //1.判断session里验证码是否正确
        if(session.getAttribute("register_code")==null || !session.getAttribute("register_code").equals(register.getVerificationCode())){
            return 400;
        }


        //2.判断手机号或账号有无重复
        if (mapper.findUserByPhoneOrAccount(register.getPhone())!=null) {
            return 0;
        }

        //3.删除session里的设置
        session.removeAttribute("register_code");
        return mapper.register(register);
    }

    @Override
    public boolean sendPhoneCode(String phone, HttpSession session) {
        String send = PhoneCode.send(phone);
        session.setAttribute("register_code",send);
        //验证码设置5分钟有效
        new Thread(()->{
            long old = System.currentTimeMillis();
            try {
                while(true) {
                    Thread.sleep(30000);
                    if (session.getAttribute("register_code") == null || System.currentTimeMillis()-old>=300000) {
                        return;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return true;
    }

}
