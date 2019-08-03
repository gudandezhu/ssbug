package com.ps.user_center.service.impl;

import com.ps.user_center.mapper.LoginMapper;
import com.ps.usercenter.service.LoginService;
import com.ps.vo.LoginVO;
import com.ps.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {


    @Autowired
    private LoginMapper mapper;

    @Override
    public UserVO login(LoginVO loginVO) {

        return mapper.findByPhoneAndPassword(loginVO);

    }
}
