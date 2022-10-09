package com.gorgeous.bookshop.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Gorgeous
 * @date 2022/10/08 15:33
 */
@Configuration
public class ShiroConfig {

    // 配置过滤器
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置静态文件不被拦截
        filterChainDefinitionMap.put("/static/**", "anon");
        //Swagger接口放行
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        //前台展示接口放行
        /*filterChainDefinitionMap.put("/contactUs/insertFeedBack", "anon");
        filterChainDefinitionMap.put("/ServiceAnnounce/selectAllAnnouncements", "anon");
        filterChainDefinitionMap.put("/ServiceAnnounce/FindServiceAnnounceById/**", "anon");
        filterChainDefinitionMap.put("/joinUs/insertCooperativePartner", "anon");*/
        //放行用户相关操作
        filterChainDefinitionMap.put("/member/**", "anon");
        filterChainDefinitionMap.put("/unit/**", "anon");
        filterChainDefinitionMap.put("/cart/**", "anon");
        filterChainDefinitionMap.put("/order/**", "anon");
        // 所有请求都需要认证
        /*filterChainDefinitionMap.put("/contactUs/**", "authc");
        filterChainDefinitionMap.put("/joinUs/**", "authc");
        filterChainDefinitionMap.put("/ServiceAnnounce/**", "authc");*/
        // 配置退出
        filterChainDefinitionMap.put("/member/logout", "logout");
        // 登录Url
        shiroFilterFactoryBean.setLoginUrl("/member/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/");
        // 未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        // 配置过滤器
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    //配置核心安全事务管理器
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shiroRealm")ShiroRealm shiroRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroRealm);
        return defaultWebSecurityManager;
    }
}
