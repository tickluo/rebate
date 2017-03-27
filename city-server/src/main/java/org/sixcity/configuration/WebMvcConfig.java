package org.sixcity.configuration;

import interceptor.StopWatchHandlerInterceptor;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import resolver.ExceptionResolver;
import resolver.MethodArgumentResolver;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@ImportResource(locations = {"classpath:application-mvc.xml"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new StopWatchHandlerInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        argumentResolvers.add(new MethodArgumentResolver());
    }

   /* @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //alibaba fastJson converter
        converters.add(new FastJsonHttpMessageConverter());
    }*/

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        //Unified exception handling
        exceptionResolvers.add(new ExceptionResolver());
    }

}
