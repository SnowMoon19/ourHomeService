package com.fxj.usermanager.controller.user;

import com.fxj.usermanager.common.results.Result;
import com.fxj.usermanager.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userServiceImpl;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result getToken(@RequestParam String username,
                           @RequestParam String password) {
        Map<String, Object> paramMap = new HashMap<>();
        System.out.println("hhh");
        paramMap.put("username", username);
        paramMap.put("password", password);
        return userServiceImpl.checkUser(paramMap);
    }
}
