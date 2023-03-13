package com.fxj.usermanager.common.results;

import lombok.Getter;

/**
 * 统一返回结果状态信息类，这里的状态码要与前端的response拦截器中对应
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),

    USER_ID_NOT_FOUND(201,"用户id不存在"),
    MAIN_ID_NOT_FOUND(202,"主键不存在"),

    SQL_INSERT_ERROR(301,"数据库插入出错"),
    SQL_UPDATE_ERROR(302,"数据库更新出错"),

    FAIL(201, "失败"),
    PARAM_ERROR( 202, "参数不正确"),
    SERVICE_ERROR(203, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    DATA_UPDATE_ERROR(205, "数据版本异常"),
    IO_ERROR(206, "文件IO出错"),

    PERMISSION(209, "没有权限"),
    LOGIN_AUTH(208, "未登陆"),

    TOKEN_EXPIRE(500, "token过期"),
    USER_VERIFY_ERROR(501, "用户信息验证失败"),
    TOKEN_NEEDED(502, "未携带token"),

//    CODE_ERROR(210, "验证码错误"),
////    LOGIN_MOBLE_ERROR(211, "账号不正确"),
//    LOGIN_DISABLED_ERROR(212, "改用户已被禁用"),
//    REGISTER_MOBLE_ERROR(213, "手机号已被使用"),
//    LOGIN_AURH(214, "需要登录"),
//    LOGIN_ACL(215, "没有权限"),
//
//    URL_ENCODE_ERROR( 216, "URL编码失败"),
//    ILLEGAL_CALLBACK_REQUEST_ERROR( 217, "非法回调请求"),
//    FETCH_ACCESSTOKEN_FAILD( 218, "获取accessToken失败"),
//    FETCH_USERINFO_ERROR( 219, "获取用户信息失败"),
//    //LOGIN_ERROR( 23005, "登录失败"),
    ;

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
