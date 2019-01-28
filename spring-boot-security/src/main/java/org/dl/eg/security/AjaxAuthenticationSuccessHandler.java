package org.dl.eg.security;

import com.alibaba.fastjson.JSON;
import org.dl.eg.bean.AjaxResponseBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yituiyun on 2019/1/26.
 */
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("200");
        responseBody.setMsg("Login Success!");
        System.out.println("=====AjaxAuthenticationSuccessHandler");
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
