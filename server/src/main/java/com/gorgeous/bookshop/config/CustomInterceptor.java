package com.gorgeous.bookshop.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        /*if (StringUtils.isEmpty(token)) {
            response.setContentType("application/json; charset=utf-8");
            try {
                PrintWriter writer = response.getWriter();
                writer.print(RespData.error("登录状态失效，请重新登录"));
                writer.close();
                response.flushBuffer();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
