package com.gorgeous.bookshop.service;

import com.gorgeous.bookshop.bean.SysMember;
import com.gorgeous.bookshop.mapper.SysMemberMapper;
import com.gorgeous.bookshop.utils.DESUtil;
import com.gorgeous.bookshop.utils.PowerJSON;
import com.gorgeous.bookshop.utils.RespData;
import com.gorgeous.bookshop.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Gorgeous
 * @date 2022/10/08 15:33
 */
@Service
public class SysMemberService {

    @Autowired
    private SysMember sysMember;

    @Autowired
    private SysMemberMapper sysMemberMapper;

    public SysMember validateUserByUsername(String username) {
        return sysMemberMapper.validateUserByUsername(username);
    }

    public RespData getCountByUsername(Map<String, Object> map){
        PowerJSON jsonObject = new PowerJSON(map);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        if (username == null || username.equals("")) {
            return RespData.buildError("用户名不能为空！", 201);
        }
        if (sysMemberMapper.getCountByUsername(username) > 0) {
            return RespData.buildError("用户名已存在！", 202);
        }

        if (password == null || password.equals("")) {
            return RespData.buildError("密码不能为空！", 203);
        }

        sysMember.setUid(UuidUtil.getUUID());
        sysMember.setUserName(username);

        String encryptedData = DESUtil.encryptBasedDes(password);

        if (encryptedData != null){
            sysMember.setPassword(encryptedData);
            sysMemberMapper.insertUser(sysMember);
            return RespData.buildSuccess("注册成功", 200);
        } else {
            return RespData.buildError("注册失败", 201);
        }
    }

}
