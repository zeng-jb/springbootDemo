package com.zeng.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 3. shiroFilterBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
            anon  无需认证就可以访问
            authc   必须认证了才可以访问
            user   必须拥有   记住我  功能才能yong
            perms   拥有对某个资源的权限的才能访问
            role    拥有某个角色权限才能访问
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

//        filterChainDefinitionMap.put("/user/add","authc");
//        filterChainDefinitionMap.put("/user/update","authc");
        //授权  ,未授权401
        filterChainDefinitionMap.put("/user/add","perms[user:add]");
        filterChainDefinitionMap.put("/user/update","perms[user:update]");

        filterChainDefinitionMap.put("/user/*","authc");
        filterChainDefinitionMap.put("/login","anon");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //设置登录界面
        bean.setLoginUrl("/tologin");
        //设置未授权的强求
        bean.setUnauthorizedUrl("/noauth");


        return bean;
    }


    // 2. dafaultWebSeccuriManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //1.创建 realm对象
    @Bean
     public UserRealm userRealm(){
            return new UserRealm();
     }


     //zhenghe  shiroDialect 用老整合thymelraf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
