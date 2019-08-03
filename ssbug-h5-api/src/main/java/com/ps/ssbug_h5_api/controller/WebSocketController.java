package com.ps.ssbug_h5_api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/websocket/{sid}")
@Component
@Slf4j
public class WebSocketController {

    //concurrent包的线程安全map，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<String,Session> map = new ConcurrentHashMap();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收 userId
    private String userId="";


    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String userId) {
        this.session = session;

        map.put(userId,this.session);     //加入map中

        log.info("有新窗口开始监听:"+userId+",当前在线人数为" + getOnlineCount());

        this.userId=userId;

        try {
        	 sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {

        map.remove(userId);  //从set中删除

        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 向某个人发送消息
     *    参数 :  对方 id
     */
    public void sendMessageByUserId(String userId , String msg) throws IOException {
        map.get(userId).getBasicRemote().sendText(msg);
    }

    /**
     * 像所有人发送消息
     *
     * */
    public void sendMessageForAll(String msg) throws IOException {
        Set<Map.Entry<String, Session>> entries = map.entrySet();
        for (Map.Entry<String, Session> entry : entries) {
            map.get(entry.getKey()).getBasicRemote().sendText(msg);
        }
        log.info("群发了一次消息--------");
    }



	/**
	 * 
	 * @param session
	 * @param error
	 */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }


    public static synchronized int getOnlineCount() {
        return map.size();
    }

}