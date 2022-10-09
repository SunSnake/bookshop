package com.gorgeous.bookshop.config;

import com.gorgeous.bookshop.bean.SysMember;
import com.gorgeous.bookshop.service.SysMemberService;
import com.gorgeous.bookshop.utils.DESUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gorgeous
 * @date 2022/10/08 15:33
 */
@Component
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysMemberService sysMemberService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token){
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //shiro判断逻辑
        String username = (String) token.getPrincipal();
        SysMember userInfo = sysMemberService.validateUserByUsername(username);
        if(userInfo == null){
            return null;
        }
        String password = DESUtil.decryptBasedDes(userInfo.getPassword());
        return new SimpleAuthenticationInfo(userInfo, password,"ShiroRealm");
    }
}
