package com.ps.ssbug_h5_api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 这个类是多例的
 *      每次有连接都会创建一个实例
 *
 * */
@ServerEndpoint("/websocket/{userId}")
@Component
@Slf4j
public class MessageWebSocket {

    private static Map map = new ConcurrentHashMap();

    private Long userId;
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId) {
        this.userId=userId;
        this.session=session;
        map.put(userId,session);

        System.out.println(userId + ": " +map.get(userId));
        log.info("有新窗口开始监听:" + userId + ",当前在线人数为" + getOnlineCount());


        try {
            session.getBasicRemote().sendText("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){

        map.remove(this.userId);

        log.info("有一连接关闭！当前在线人数为:" + getOnlineCount());
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


    public synchronized int getOnlineCount() {
        return map.size();
    }

    public static Map getMap() {
        return map;
    }

}