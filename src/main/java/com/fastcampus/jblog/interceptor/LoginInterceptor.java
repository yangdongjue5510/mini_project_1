package com.fastcampus.jblog.interceptor;

import com.fastcampus.jblog.biz.user.UserVO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {

    private final List<String> excludePath = List.of("/", "/loginView", "/login", "/searchBlog", "/css/**", "/images/**");

    public List<String> getExcludePath() {
        return excludePath;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserVO user = (UserVO)request.getSession().getAttribute("user");
        if(user == null) {
            response.sendRedirect("/loginView");
        }
        return user != null;
    }
}
