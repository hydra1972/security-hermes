package org.dl.eg.security;

import org.dl.eg.bean.AjaxAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //  启用方法级别的权限认证
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AjaxAuthenticationEntryPoint authenticationEntryPoint;  //  未登陆时返回 JSON 格式的数据给前端（否则为 html）

    @Autowired
    AjaxAuthenticationSuccessHandler authenticationSuccessHandler;  // 登录成功返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    AjaxAuthenticationFailureHandler authenticationFailureHandler;  //  登录失败返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    AjaxLogoutSuccessHandler  logoutSuccessHandler;  // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）

    @Autowired
    AjaxAccessDeniedHandler accessDeniedHandler;    // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()  //定义哪些url需要保护，哪些url不需要保护
                .antMatchers("/", "/message/").permitAll()    //定义不需要认证就可以访问
                .anyRequest().authenticated()
                .and()
                .formLogin().usernameParameter("user").passwordParameter("pwd")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .loginPage("/login")  //定义当需要用户登录时候，转到的登录页面
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
    }

    @Bean
     public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
