package com.gorgeous.bookshop.controller;

import com.gorgeous.bookshop.service.SysMemberService;
import com.gorgeous.bookshop.utils.PowerJSON;
import com.gorgeous.bookshop.utils.RespData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @ApiOperation("使用账号密码登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object loginAction(@RequestBody Map<String, Object> map){
        PowerJSON jsonObject = new PowerJSON(map);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken userToken=new UsernamePasswordToken(username, password);
        try{
            currentUser.login(userToken);
        }catch (UnknownAccountException e){
            return RespData.buildError("用户名不存在", 202);
        }catch (IncorrectCredentialsException e){
            return RespData.buildError("密码错误", 201);
        }
        return RespData.buildSuccess("登录成功", 200);
    }

    @ApiOperation("注销登出")
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public RespData logoutAction(){
        SecurityUtils.getSubject().logout();
        return RespData.buildSuccess("注销成功", 200);
    }

    @ApiOperation("用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RespData registerAction(@RequestBody Map<String, Object> map) {
        return sysMemberService.getCountByUsername(map);
    }
}
