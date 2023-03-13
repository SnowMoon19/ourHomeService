package com.fxj.usermanager.service.user;

import com.fxj.usermanager.common.results.Result;

import java.util.Map;

public interface UserService {

    Result checkUser(Map<String, Object> paramMap);
}
