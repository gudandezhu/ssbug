package com.ps.ssbug_h5_api.controller;

import com.ps.usercenter.service.RegisterService;
import com.ps.vo.MessageVO;
import com.ps.vo.RegisterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@Api(tags = "注册接口类")
public class RegisterController {

    @Reference
    private RegisterService service;

    @ApiOperation("注册")
    @PostMapping("/register")
    public MessageVO login(@RequestBody RegisterVO register, HttpSession session){

        log.info("注册方法收到参数:{}",register);
        MessageVO mv = new MessageVO<>();
        int add = service.register(register,session);

        if(add==0){
            mv.setCode(0);
            mv.setMsg("手机号或账号已存在");
        }else if(add==400){
            mv.setCode(0);
            mv.setMsg("验证码错误");
        }else if(add!=1){
            mv.setCode(0);
            mv.setMsg("注册失败,服务器错误,稍后重试...");
        }

        return mv;
    }

    @ApiOperation("注册发送验证码")
    @GetMapping("/sendPhoneCode/{phone}/{code}")
    public MessageVO sendPhoneCode(@PathVariable String phone, HttpSession session){
        MessageVO<Object> mv = new MessageVO<>();
        if(!service.sendPhoneCode(phone,session)){
            mv.setCode(0);
            mv.setMsg("发送验证码错误");
        }
        return mv;
    }

}
