package com.fxj.usermanager.mapper.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxj.usermanager.entity.dao.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    User selectUserByPwd(String username, String password);

//    User selectByUserId(Integer userId);
}

