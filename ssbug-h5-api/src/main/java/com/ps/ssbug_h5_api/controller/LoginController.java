package com.ps.ssbug_h5_api.controller;

import com.ps.usercenter.service.LoginService;
import com.ps.vo.LoginVO;
import com.ps.vo.MessageVO;
import com.ps.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "登录api")
@RequestMapping("/login")
public class LoginController {

    @Reference
    private LoginService service;

    @ApiOperation("登录")
    @PostMapping("/login")
    public MessageVO login(@RequestBody LoginVO loginVO){

        log.info("登录方法收到参数:{}",loginVO);
        MessageVO mv = new MessageVO<>();
        UserVO user = service.login(loginVO);

        if(user==null){
            mv.setCode(0);
            mv.setMsg("账号或密码错误");
        }

        mv.setData(user);

        return mv;
    }



}
