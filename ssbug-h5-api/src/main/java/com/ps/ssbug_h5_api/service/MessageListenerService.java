package com.ps.ssbug_h5_api.service;

import com.ps.ssbug_h5_api.controller.MessageWebSocket;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;

@Component
@RequestMapping("/test1")
@Api(tags = "admin专调接口")
public class MessageListenerService {

    private Map<Object, Session> map = MessageWebSocket.getMap();

//    @KafkaListener(topics = "notify")
//    public void process(ConsumerRecord cr) throws IOException {
//        map.get(cr.value()).getBasicRemote().sendText("发送给你消息");
//      ystem.out.println("接收到消息 : "+cr.value());
//    }

//    @GetMapping("/notify/{userId}")
//    public void notifyTest(@PathVariable Long userId){
//        try {
//            map.get(userId).getBasicRemote().sendText("发送给"+userId+"消息");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
