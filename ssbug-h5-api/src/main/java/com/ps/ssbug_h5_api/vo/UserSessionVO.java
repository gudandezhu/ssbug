package com.ps.ssbug_h5_api.vo;

import lombok.Data;

import javax.websocket.Session;
import java.io.Serializable;

@Data
public class UserSessionVO implements Serializable {

    private static final long serialVersionUID = 76980618725128808L;

    private String userId;
    private Session session;

}
