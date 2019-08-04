package com.ps.ssbug_h5_api.controller;

import com.ps.ssbug_h5_api.vo.UserSessionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint("/websocket/{userId}")
@Component
@Slf4j
public class WebSocketController {


    //redis存放session
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    private ListOperations list = redisTemplate.opsForList();


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        UserSessionVO uVO = new UserSessionVO();
        uVO.setUserId(userId);
        uVO.setSession(session);


        list.leftPush("session", uVO);

        log.info("有新窗口开始监听:" + userId + ",当前在线人数为" + getOnlineCount());


        try {
            session.getBasicRemote().sendText("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }

        log.info("当前用户信息:" + list.range("session", 0, 100));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) throws IOException {

        list.remove("session", 1, session);

        log.info("有一连接关闭！当前在线人数为:" + getOnlineCount());
        log.info("当前用户信息:" + list.range("session", 0, 100));

        session.getBasicRemote().sendText("连接关闭");
    }

    /**
     * 接收信息的方法
     */
    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println(msg);
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }


    public synchronized Long getOnlineCount() {
        return list.size("session");
    }

}