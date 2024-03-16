package com.platform.service;

import com.platform.domain.Result;
import com.platform.domain.User;
import com.platform.utils.resp.ResponseObj;

public interface UserService {
    Result regist(User user);
    ResponseObj login(User user);
}
