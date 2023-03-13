package com.fxj.recordmanager.entity.dao.user;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@TableName("user")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Integer id;

    private Integer userId;

    private String userName;

    private String password;

    @TableField(fill = FieldFill.INSERT)
    private String gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String gmtModified;

    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
