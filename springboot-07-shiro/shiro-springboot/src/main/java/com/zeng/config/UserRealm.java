package com.zeng.config;

import com.zeng.pojo.User;
import com.zeng.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


//自定义 realm
public class UserRealm  extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了===》》授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //info.addStringPermission("user:add");

        //拿到当前登录对象
        Subject subject = SecurityUtils.getSubject();
        User principal = (User)subject.getPrincipal();
        //设置当前用户权限
        info.addStringPermission(principal.getPerms());


        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了===》》认证doGetAuthorizationInfo");

        //用户名 密码 数据中取
//        String name = "root";
//        String password = "123";


        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        //连接真实数据库
        User user = userService.queryUserByName(usernamePasswordToken.getUsername());
        if(user == null){
            return null;//没有这个人
        }


//        if (!usernamePasswordToken.getUsername().equals(name)){
//            return null;  //抛出异常 用户名不对
//        }
//
        //密码认证，shiro
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");


    }
}
