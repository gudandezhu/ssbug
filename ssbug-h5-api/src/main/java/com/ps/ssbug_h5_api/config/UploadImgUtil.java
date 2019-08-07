package com.ps.ssbug_h5_api.config;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 *  七牛云上传文件 util类
 *      此类依赖于  com.ps.configuration.QiniuUploadFileConfig  类
 */
@Component
@Slf4j
public class UploadImgUtil {

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    @Autowired
    private Auth auth;

    @Value("${qiniu.img.Bucket}")
    private String bucket;
    @Value("${qiniu.cdn.img.prefix}")
    private String cdnPrefix;


    /**
     * 定义七牛云上传的相关策略
     */
    private StringMap putPolicy;

    /***
     * @param is
     * @param fileName
     * @return 可以访问的url
     * @throws Exception
     */
    @SuppressWarnings("all")
    private String uploadImgPrivate(InputStream is,String fileName) throws Exception {
        Response response = this.uploadManager.put(is, fileName, getUploadToken(), null, null);
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(is, fileName, getUploadToken(), null, null);
            retry++;
        }
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("上传成功,图片地址:"+cdnPrefix+"/"+fileName);
        log.info("秘钥hash : "+putRet.hash);
        return cdnPrefix+"/"+fileName;
    }

    public String uploadImg(MultipartFile upload, String fileName) throws Exception {
        InputStream is = upload.getInputStream();
        String url = uploadImgPrivate(is,fileName);
        is.close();
        return url;
    }

    /**
     * 获取上传凭证
     * @return
     */
    private String getUploadToken() {
        return this.auth.uploadToken(bucket, null, 3600, putPolicy);
    }
}
