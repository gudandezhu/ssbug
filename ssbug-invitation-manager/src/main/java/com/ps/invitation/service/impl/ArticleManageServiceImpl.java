package com.ps.invitation.service.impl;


import com.ps.dto.ArticleDTO;
import com.ps.invitation.service.ArticleManageService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@Service
public class ArticleManageServiceImpl implements ArticleManageService {


    @Autowired
    private MongoTemplate template;

    @Override
    public boolean addArticle(ArticleDTO articleDTO) {
        ArticleDTO insert = template.insert(articleDTO);
        return insert!=null;
    }


}
