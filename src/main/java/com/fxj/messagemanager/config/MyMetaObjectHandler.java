package com.fxj.messagemanager.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.fxj.messagemanager.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    //插入时填充
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtCreate", String.valueOf(System.currentTimeMillis()), metaObject);
        this.setFieldValByName("gmtModified", String.valueOf(System.currentTimeMillis()), metaObject);
        this.setFieldValByName("deleted", Constants.NOT_DELETED, metaObject);
    }
    //更新时填充
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", String.valueOf(System.currentTimeMillis()), metaObject);
    }
}