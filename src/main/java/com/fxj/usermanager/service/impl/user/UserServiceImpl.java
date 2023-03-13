package com.fxj.usermanager.service.impl.user;

import com.fxj.usermanager.common.results.Result;
import com.fxj.usermanager.entity.dao.user.User;
import com.fxj.usermanager.entity.vo.UserWithToken;
import com.fxj.usermanager.mapper.user.UserMapper;
import com.fxj.usermanager.service.user.UserService;
import com.fxj.usermanager.utils.auth.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result checkUser(Map<String, Object> paramMap) {
        String username = (String) paramMap.get("username");
        String password = (String) paramMap.get("password");

        User user = userMapper.selectUserByPwd(username, password);

        if (user != null) {
            String token = JwtUtils.createToken(String.valueOf(user.getUserId()), user.getUserName());
            UserWithToken user1 = new UserWithToken();
            // 除了用户名和用户id，其他信息不返回
            User returnUser = new User();
            returnUser.setUserId(user.getUserId());
            returnUser.setUserName(user.getUserName());
            user1.setUser(returnUser);
            user1.setToken(token);
            return Result.ok(user1);
        }
        return Result.fail();
    }
}

