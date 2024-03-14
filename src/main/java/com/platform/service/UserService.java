package com.platform.service;

import com.platform.domain.Result;
import com.platform.domain.User;

public interface UserService {
    Result regist(User user);
    Result login(User user);
}
