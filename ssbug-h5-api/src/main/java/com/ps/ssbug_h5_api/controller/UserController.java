package com.ps.ssbug_h5_api.controller;

import com.alibaba.fastjson.JSONObject;
import com.ps.dto.HistoryDTO;
import com.ps.usercenter.service.HistoryService;
import com.ps.vo.MessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户相关接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @ApiOperation("创建一个历史足迹")
    @PostMapping("/addHistory")
    public MessageVO addHistory(@RequestBody HistoryDTO historyDTO){
        //发送消息
        kafkaTemplate.send("history",JSONObject.toJSONString(historyDTO));
        return new MessageVO<>();
    }


    @Reference
    private HistoryService historyService;


    @ApiOperation("查询自己的历史足迹")
    @GetMapping("/findHistoryAll/{userId}")
    public MessageVO findHistoryAll(@PathVariable int userId){
        MessageVO<Object> mv = new MessageVO<>();
        List<HistoryDTO> list =  historyService.findHistoryAll(userId);
        mv.setData(list);
        return mv;
    }

}
