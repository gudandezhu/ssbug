package com.ps.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@Document(collection = "article")
@ApiModel(description = "文章响应数据")
public class ArticleDTO implements Serializable {

    private static final long serialVersionUID = -7060925335197562253L;

    private String _id;
    @ApiModelProperty(value = "标题")
    private String title;               //标题
    @ApiModelProperty(value = "技术类型")
    private String[] technologyTypes; //技术类型
    @ApiModelProperty(value = "当前版本")
    private String[] versions;        //当前版本
    @ApiModelProperty(value = "运行环境  window  linux")
    private String runEnvironment;   //运行环境  window  linux
    @ApiModelProperty(value = "依赖技术")
    private String[] dependencies;   //依赖技术
    @ApiModelProperty(value = "类型 , 0本站  1外站")
    private int type;                //类型 , 0本站  1外站

    @ApiModelProperty(value = "本站文章存放地址")
    @Field("storage_url")
    private String storageUrl;       //本站文章存放地址

    @ApiModelProperty(value = "外站文章链接")
    @Field("collect_url")
    private String collectUrl;       //外站文章链接

    /*mongodb忽略字段*/
    @Transient
    private String html;

}
