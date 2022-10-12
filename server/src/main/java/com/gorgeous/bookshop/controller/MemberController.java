package com.gorgeous.bookshop.controller;

import com.gorgeous.bookshop.constant.BookConstant;
import com.gorgeous.bookshop.constant.PowerJSON;
import com.gorgeous.bookshop.constant.RespData;
import com.gorgeous.bookshop.service.SysMemberService;
import com.gorgeous.bookshop.utils.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Gorgeous
 * @date 2022/10/08 15:33
 */
@RestController
@RequestMapping("/member")
@Api(tags = "会员登陆注册接口")
public class MemberController {

    @Autowired
    SysMemberService sysMemberService;

    @ApiOperation("wx登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object loginAction(@RequestBody @Valid PowerJSON request){
        String code = request.getString("code");
        if (StringUtils.isEmpty(code)) {
            return RespData.error("未能获取登录状态码，请重新进入小程序");
        }

        String result = HttpUtil.get(BookConstant.wxValidUrl + code);
        if (StringUtils.isEmpty(result)) {
            return RespData.error("获取登录状态失败，请联系管理员");
        }

        PowerJSON resultJson = new PowerJSON(result);
        String sessionKey = resultJson.getString("session_key");
        String openid = resultJson.getString("openid");

        return RespData.success("登录成功", sessionKey);
    }

}
