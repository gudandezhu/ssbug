package com.ps.ssbug_h5_api.controller;

import com.ps.dto.ArticleDTO;
import com.ps.search.service.SearchService;
import com.ps.vo.MessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "搜索 api")
@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {



    @Reference
    private SearchService service;

    @ApiOperation("通过id查询文章")
    @RequestMapping("/findById/{id}")
    public MessageVO findById(@PathVariable String id){
        MessageVO<Object> mv = new MessageVO<>();

        //查询文章所有信息  byId
        ArticleDTO articleDTO = service.findById(id);

        mv.setData(articleDTO);

        return mv;
    }


    @ApiOperation("关键字模糊搜索")
    @RequestMapping("/findByKeyWord")
    public MessageVO findByKeyWord(){
        MessageVO<Object> mv = new MessageVO<>();


        return mv;
    }
}
