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
@RequestMapping("/register")
public class RegisterController {

    @Reference
    private RegisterService service;

    @ApiOperation("注册")
    @PostMapping("/register")
    public MessageVO login(@RequestBody RegisterVO register, HttpSession session){

        log.info("注册方法收到参数:{}",register);
        MessageVO mv = new MessageVO<>();


        //1.判断session里验证码是否正确
        if(session.getAttribute("register_code")==null || !session.getAttribute("register_code").equals(register.getVerificationCode())){
            mv.setCode(0);
            mv.setMsg("验证码错误");
            return mv;
        }

        //2. 注册服务
        int add = service.register(register);

        if(add==0){
            mv.setCode(0);
            mv.setMsg("手机号或账号已存在");
        }else if(add!=1){
            mv.setCode(0);
            mv.setMsg("注册失败,服务器错误,稍后重试...");
        }

        //3.删除session里的设置
        session.removeAttribute("register_code");

        return mv;
    }



    @ApiOperation("注册发送验证码")
    @GetMapping("/sendPhoneCode/{phone}")
    public MessageVO sendPhoneCode(@PathVariable String phone, HttpSession session){

        //1.发送验证码
        String send = service.sendPhoneCode(phone);

        //2.将验证码放入session
        session.setAttribute("register_code",send);

        //3.验证码设置5分钟有效
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

        return new MessageVO<>();
    }

}
