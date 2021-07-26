package com.laotan.net.common;

import com.laotan.net.common.util.RedisUtils;
import com.laotan.net.service.AccountService;
import com.laotan.net.service.UserTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 拦截器
 * @author TTXC
 */
//@Configuration
public class ReqInterceptor extends WebMvcConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AccountService accountService;
    @Autowired
    UserTokenService userTokenService;
    @Autowired
    RedisUtils redisUtils;

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/app/login/login");
        addInterceptor.excludePathPatterns("/app/login/setPassword");
        addInterceptor.excludePathPatterns("/static/**");
        //swagger不拦截
        addInterceptor.excludePathPatterns("/swagger**/**");
        addInterceptor.excludePathPatterns("/webjars**/**");
        addInterceptor.excludePathPatterns("/platform**/**");
        addInterceptor.excludePathPatterns("/doc.html");
        // 临时调试使用
        addInterceptor.excludePathPatterns("/h5/**");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            String token = request.getHeader("token");
            if(StringUtils.isEmpty(token)){
                response.sendError(400,"token不能为空");
                return false;
            }
            JsonResult jsonResult = userTokenService.authToken(token);
            if(jsonResult.getCode() != 10000){
                response.sendError(jsonResult.getCode(),jsonResult.getMessage());
                return false;
            }
            HttpSession session = request.getSession();
            //放入session
            session.setAttribute("token",token);
            //放入redis中
            redisUtils.setCacheObject(token,jsonResult.getContent());
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            HttpSession session = request.getSession();
        }

    }


    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        // 解决中文乱码问题
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    /**
     * 获取文件流
     *
     * @param url
     * @return
     */
    public InputStream getFileStream(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return inputStream;
            }
        } catch (IOException e) {
            logger.error("获取文件异常: " + e.getLocalizedMessage() + " " + url);
        }
        return null;
    }
}
