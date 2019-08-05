package com.ps.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "history")
public class HistoryDTO  implements Serializable {

    private static final long serialVersionUID = 5418621759380807377L;

    private String _id;
    private Long userId;
    private String title;
    private String url;
    private Date createTime;

}
