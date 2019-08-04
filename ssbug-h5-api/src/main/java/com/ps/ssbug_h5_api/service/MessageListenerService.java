package com.ps.ssbug_h5_api.service;

import com.ps.ssbug_h5_api.controller.MessageWebSocket;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/test1")
public class MessageListenerService {

    private Map<Object, Session> map = MessageWebSocket.getMap();

//    @KafkaListener(topics = "notify")
//    public void process(ConsumerRecord cr) throws IOException {
//        map.get(cr.value()).getBasicRemote().sendText("发送给你消息");
//        System.out.println("接收到消息 : "+cr.value());
//    }

    @RequestMapping("/notify/{userId}")
    public void notifyTest(@PathVariable Long userId){
        try {
            map.get(userId).getBasicRemote().sendText("发送给你消息");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
