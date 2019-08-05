package com.ps.ssbug_h5_api.controller;

import com.ps.dto.ArticleDTO;
import com.ps.invitation.service.ArticleManageService;
import com.ps.vo.MessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文章模块接口")
@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {


    @Reference
    private ArticleManageService articleManageService;

    @ApiOperation("发布帖子")
    @PostMapping("/addArticle")
    public MessageVO addArticle(@RequestBody ArticleDTO articleDTO) {
        MessageVO<Object> mv = new MessageVO<>();
        boolean bool = articleManageService.addArticle(articleDTO);
        if (!bool) {
            mv.setCode(0);
            mv.setMsg("发布失败 , 请重试");
        }
        return mv;
    }


    /**
     * 图片上传
     *
     * @param imgFile
     * @return
     */
    @PostMapping("/uploadPicture")
    public MessageVO uploadPicture(@RequestParam("imgFile")MultipartFile imgFile) {
        MessageVO<Object> mv = new MessageVO<>();
        if (imgFile == null) {
            mv.setCode(0);
            mv.setMsg("文件没接收到");
            return mv;
        }
        log.debug("file -> {},viewId ->{}", imgFile.getOriginalFilename());

        String fileOriName = imgFile.getOriginalFilename();// 获取原名称

        return mv;
    }
    /**
     * 处理文本数据的请求（即表单数据，但不包括图片）
     * @param content
     * @return
     */
    @GetMapping("/submitForm")
    public String uploadForm(String content) {
        System.out.println(content);

        return "ok";
    }


}
