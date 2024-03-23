package com.platform.service;

import com.platform.domain.Result;
import com.platform.domain.vo.LoginUserVO;
import com.platform.domain.vo.RegistUserVO;
import com.platform.utils.resp.ResponseObj;

public interface UserService {

    Result regist(RegistUserVO user);
    ResponseObj login(LoginUserVO user);


}
