package com.ps.user_center.mapper;

import com.ps.vo.RegisterVO;
import com.ps.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {

    int register(RegisterVO register);

    UserVO findUserByPhoneOrAccount(String phone);

}
