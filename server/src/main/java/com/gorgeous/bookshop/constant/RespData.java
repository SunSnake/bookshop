package com.gorgeous.bookshop.constant;

import cn.hutool.core.util.ZipUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author Gorgeous
 * @date 2022/10/08 15:33
 */
public class RespData extends PowerJSON {

    public static final int ERROR = -1;
    public static final int SUCCESS = 1;

    private RespData(Integer status, Object data, String msg) {
        put("status", status);
        put("data", data);
        put("msg", msg);
    }

    // 失败，传入描述信息
    public static RespData error(String msg) {
        return new RespData(ERROR, null, msg);
    }

    // 成功，传入描述信息
    public static RespData success(String msg) {
        return new RespData(null,null,  msg);
    }

    // 成功，传入状态码,数据
    public static RespData success(String msg, Object data) {
        return new RespData(SUCCESS,data,  msg);
    }

    public static String toStringGzip(Object obj) {
        return new String(ZipUtil.gzip(obj.toString(), StandardCharsets.UTF_8.name()), StandardCharsets.ISO_8859_1);
    }

}
