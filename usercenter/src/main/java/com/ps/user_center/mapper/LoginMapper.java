package com.ps.user_center.mapper;

import com.ps.vo.LoginVO;
import com.ps.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    UserVO findByPhoneAndPassword(LoginVO loginVO);
}
