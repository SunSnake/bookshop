package com.gorgeous.bookshop.utils;

import cn.hutool.core.util.ZipUtil;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * @author Gorgeous
 * @date 2022/10/08 15:33
 */
public class RespData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code; // 状态码
    private Object data; // 数据
    private String msg;// 描述

    public RespData() {
    }

    private RespData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    // 失败，传入描述信息
    public static RespData buildError(String msg) {
        return new RespData(null, null, msg);
    }

    // 失败，传入描述信息,状态码
    public static RespData buildError(String msg, Integer code) {
        return new RespData(code, null, msg);
    }

    // 成功，传入描述信息
    public static RespData buildSuccess(String msg) {
        return new RespData(null,null,  msg);
    }

    // 成功，传入状态码,及描述信息
    public static RespData buildSuccess(String msg, Integer code) {
        return new RespData(code,null,  msg);
    }

    // 成功，传入状态码,数据及描述信息
    public static RespData buildSuccess(Integer code, Object data, String msg) {
        return new RespData(code, data, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RespData [code=" + code + ", data=" + data + ", msg=" + msg  + "]";
    }

    public static String toStringGzip(Object obj) {
        return new String(ZipUtil.gzip(obj.toString(), "ISO-8859-1"), StandardCharsets.ISO_8859_1);
    }

}
