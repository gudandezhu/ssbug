package com.ps.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class HistoryDTO  implements Serializable {

    private static final long serialVersionUID = 5418621759380807377L;

    private Long userId;
    private String title;
    private String url;
    private Date createTime;

}
