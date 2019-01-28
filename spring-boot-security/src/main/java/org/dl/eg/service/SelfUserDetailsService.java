package org.dl.eg.service;

import org.dl.eg.bean.SelfUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yituiyun on 2019/1/27.
 */
@Component
public class SelfUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //构建用户信息的逻辑(取数据库/LDAP等用户信息)
        SelfUserDetails userInfo = new SelfUserDetails();
        userInfo.setUsername(username); // 任意用户名登录

     /*   Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        String encodePassword = md5PasswordEncoder.encodePassword("123", username); // 模拟从数据库中获取的密码原为 123
        userInfo.setPassword(encodePassword);

        Set authoritiesSet = new HashSet();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN"); // 模拟从数据库中获取用户角色

        authoritiesSet.add(authority);
        userInfo.setAuthorities(authoritiesSet);*/

        return null;
    }
}