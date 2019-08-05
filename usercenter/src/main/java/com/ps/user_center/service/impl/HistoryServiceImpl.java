package com.ps.user_center.service.impl;

import com.ps.dto.HistoryDTO;
import com.ps.usercenter.service.HistoryService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private MongoTemplate template;

    @Override
    public List<HistoryDTO> findHistoryAll(int userId) {
        return template.find(query(where("userId").is(userId)).with(new Sort(new Sort.Order(Sort.Direction.ASC.DESC,"createTime"))),HistoryDTO.class);
    }
}
