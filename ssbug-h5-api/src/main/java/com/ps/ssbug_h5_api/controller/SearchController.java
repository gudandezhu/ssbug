package com.ps.ssbug_h5_api.controller;

import com.ps.dto.ArticleDTO;
import com.ps.search.service.SearchService;
import com.ps.ssbug_h5_api.vo.SearchVO;
import com.ps.vo.MessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "搜索 api")
@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {



    @Reference
    private SearchService service;

    @ApiOperation("通过id查询文章")
    @GetMapping("/findById/{id}")
    public MessageVO findById(@PathVariable String id){
        MessageVO<Object> mv = new MessageVO<>();

        //查询文章所有信息  byId
        ArticleDTO articleDTO = service.findById(id);

        mv.setData(articleDTO);

        return mv;
    }

    @ApiOperation("排行榜 api")
    @GetMapping("/findRankingList/{num}/{}")
    public void findRankingList(@PathVariable int num){

    }

    @ApiOperation("关键字模糊搜索")
    @PostMapping("/findByKeyWord")
    public MessageVO findByKeyWord(@RequestBody SearchVO searchVO){
        MessageVO<Object> mv = new MessageVO<>();
        if(StringUtils.isEmpty(searchVO.getKeyWord())){
            mv.setMsg("请输入内容搜索");
            return mv;
        }
        List<ArticleDTO> list = service.findByKeyWord(searchVO.getKeyWord(), searchVO.getParams());
        mv.setData(list);
        return mv;
    }
}
