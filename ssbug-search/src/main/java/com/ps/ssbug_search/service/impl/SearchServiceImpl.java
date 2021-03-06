package com.ps.ssbug_search.service.impl;

import com.ps.dto.ArticleDTO;
import com.ps.search.service.SearchService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {



    @Value("${qiniu.cdn.article.prefix}")
    private String CDN_ARTICLE_PREFIX;

    @Autowired
    private MongoTemplate template;



    @Override
    public ArticleDTO findById(String id) {

        ArticleDTO dto = template.findById(id, ArticleDTO.class);
        dto.setStorageUrl(CDN_ARTICLE_PREFIX+"/"+dto.getStorageUrl());

        return dto;
    }

    //by关键字和参数 搜索服务
    @Override
    public List<ArticleDTO> findByKeyWord(String keyWord, String[] params) {


        return null;
    }
}
