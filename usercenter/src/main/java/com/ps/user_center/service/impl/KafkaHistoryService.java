package com.ps.user_center.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ps.dto.HistoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaHistoryService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @KafkaListener(topics = "history")
    public void createHistoryRecord(String msg){
        log.info("kafka接收 : "+msg);
        HistoryDTO historyDTO = JSONObject.toJavaObject(JSONObject.parseObject(msg), HistoryDTO.class);

        mongoTemplate.insert(historyDTO);

        log.info("mongodb insert:"+historyDTO);
    }

}
