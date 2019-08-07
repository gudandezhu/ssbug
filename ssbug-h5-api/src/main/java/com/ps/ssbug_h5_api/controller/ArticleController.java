package com.ps.ssbug_h5_api.controller;

import com.ps.dto.ArticleDTO;
import com.ps.invitation.service.ArticleManageService;
import com.ps.ssbug_h5_api.config.UploadArticleUtil;
import com.ps.ssbug_h5_api.config.UploadImgUtil;
import com.ps.vo.MessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api(tags = "文章模块接口")
@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {


    @Value("${ssbug.article.url}")
    private String ARTICLE_URL_PRE;

    @Autowired
    private UploadImgUtil uploadImgUtil;

    @Autowired
    private UploadArticleUtil uploadArticleUtil;


    @Reference
    private ArticleManageService articleManageService;

    @ApiOperation("发布帖子 , 内容有html格式的帖子")
    @PostMapping("/addArticle")
    public MessageVO addArticle(@RequestBody ArticleDTO articleDTO) throws Exception {
        MessageVO<Object> mv = new MessageVO<>();

        String fileName = UUID.randomUUID() + ".html";
        FileOutputStream fos = new FileOutputStream(ARTICLE_URL_PRE + "/" + fileName);
        fos.write(fileName.getBytes());

        FileInputStream fis = new FileInputStream(ARTICLE_URL_PRE + "/" + fileName);
        //上传到七牛云
        String url = uploadArticleUtil.uploadArticle(fis, fileName);
        //存储到mongodb数据库
        boolean bool = articleManageService.addArticle(articleDTO);


        if (!bool) {
            mv.setCode(0);
            mv.setMsg("发布失败 , 请重试");
        }
        return mv;
    }


    @ApiOperation("图片上传")
    @SuppressWarnings("all")
    @PostMapping("/uploadPicture")
    public Map<String, Object> uploadPicture(@RequestParam("file") MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();
        System.out.println(name);

        HashMap<String, Object> mv = new HashMap<>();

        //重命名图片名称
        String fileName = "img" + System.currentTimeMillis() + "." + name.split("\\.")[1];
        String url = uploadImgUtil.uploadImg(file, fileName);
        mv.put("state", "SUCCESS");
        mv.put("url", url);
        mv.put("title", fileName);
        mv.put("original", fileName);
//        HashMap<Object, Object> mv2 = new HashMap<>();
//        mv2.put("url",url);
//        mv.put("list",mv2);
//        mv.put("start",0);
//        mv.put("total",1);
        mv.put("code", 200);
        return mv;
    }


    @ApiOperation("图片批量上传")
    @SuppressWarnings("all")
    @PostMapping("/uploadPictureList")
    public Map<String, Object> uploadPictureList(@RequestParam("file") MultipartFile[] files) throws Exception {

        HashMap<String, Object> mv = new HashMap<>();
//
//        //重命名图片名称
//        String fileName  ="img" + System.currentTimeMillis()+"."+name.split("\\.")[1];
//        String s = uploadImgUtil.uploadImg(file, fileName);
//        mv.put("state","SUCCESS");
//        mv.put("url",s);
//        mv.put("title",fileName);
//        mv.put("original",fileName);
        return mv;
    }



}
