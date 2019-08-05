package com.ps.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@Document(collection = "article")
public class ArticleDTO implements Serializable {

    private static final long serialVersionUID = -7060925335197562253L;

    private String _id;
    private String title;               //标题
    private String[] technologyTypes; //技术类型
    private String[] versions;        //当前版本
    private String runEnvironment;   //运行环境  window  linux
    private String[] dependencies;   //依赖技术
    private int type;                //类型 , 0本站  1外站

    @Field("storage_url")
    private String storageUrl;       //本站文章存放地址

    @Field("collect_url")
    private String collectUrl;       //外站文章链接

}
