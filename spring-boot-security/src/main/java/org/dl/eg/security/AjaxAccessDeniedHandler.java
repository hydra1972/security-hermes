package org.dl.eg.security;

import com.alibaba.fastjson.JSON;
import org.dl.eg.bean.AjaxResponseBody;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yituiyun on 2019/1/27.
 */
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("300");
        responseBody.setMsg("Need Authorities!");
        System.out.println("=====AjaxAccessDeniedHandler");
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}