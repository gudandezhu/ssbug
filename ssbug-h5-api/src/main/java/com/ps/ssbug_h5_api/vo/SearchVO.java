package com.ps.ssbug_h5_api.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchVO implements Serializable {

    private static final long serialVersionUID = -7611024579634104677L;

    //搜索关键字
    private String keyWord;
    //搜索可能有的参数
    private String[] params;
}
