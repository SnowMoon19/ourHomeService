package com.fxj.recordmanager.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fxj.recordmanager.entity.dao.user.User;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserWithToken {
    private User user;

    private String token;
}
