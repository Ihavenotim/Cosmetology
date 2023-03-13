package com.cqjtu.cosmetology.util;

public enum ResponseCode {

    SUCCESS01("200","请求成功"),
    SUCCESS02("8001","注册成功"),
    SUCCESS03("8003","登录成功"),
    SUCCESS04("8004","预约成功"),
    FAIL01("9999","数据库断开连接"),
    FAIL02("9997","用户名为空"),
    FAIL03("9996","未查询到商品"),
    FAIL04("9996","未查询到商品"),
    FAIL05 ("9995","用户名已注册"),
    FAIL06("9994","用户名密码错误");


    private String code;
    private String msg;

    ResponseCode(){}

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
