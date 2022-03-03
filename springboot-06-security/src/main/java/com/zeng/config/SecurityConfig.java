package com.zeng.config;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//AOP
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问
        //功能页只有对应权限的人访问，，，，，链式编程

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");


        //没有权限会默认到登录页面/注意是/login请求
        //定制登录页loginPage("/toLogin")
        //loginProcessingUrl("/login")请求登录的正式url，注意是post
        /*
         &lt;label for="username"&gt;Username&lt;/label&gt;
	 *        &lt;input type="text" id="username" name="username"/&gt;
	 *        &lt;/p&gt;
	 *        &lt;p&gt;
	 *        &lt;label for="password"&gt;Password&lt;/label&gt;
	 *        &lt;input type="password" id="password" name="password"/&gt;
	 在函数里默认接收的是username和password，如果前端不是这两个可以使用usernameParameter()and   passwordParemeter()方法
         */
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login");

        http.csrf().disable();//关闭访问攻击
        //注销开启,.跳转到首页
        http.logout().logoutSuccessUrl("/");

        //开启记住我功能,默认保存的cookie两周,rememberMeParameter自定义
        http.rememberMe().rememberMeParameter("remember");
    }


    //认证,,springboot 2.1.x 可以直接使用
    //密码编码There is no PasswordEncoder mapped for the id "null"
    //在spring security 5.0+ 新增了很多加密方法  MD5
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("zeng").password(new BCryptPasswordEncoder().encode("123")).roles("vip2","vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123")).roles("vip1","vip2","vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123")).roles("vip1");
    }
}
