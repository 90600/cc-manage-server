package cc.insist.manage.config;

import cc.insist.manage.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: cc
 * @Date: 2023/11/29 2:56 PM
 * @Description:
 */
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())
                //拦截的路径
//                .addPathPatterns("/api/user/v1/login", "/api/prodcut/*/lock_products")

                //排查不拦截的路径
                .excludePathPatterns("/api/user/v1/login", "/api/user/v1/register", "/doc.html","/webjars/**","/favicon.ico","/v3/**");

    }
}
