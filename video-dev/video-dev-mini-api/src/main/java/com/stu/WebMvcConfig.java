package com.stu;

import com.stu.controller.interceptor.MiniIntercepto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("file:F:/stu_videos_dev/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(miniIntercepto()).addPathPatterns("/user/**").addPathPatterns("/bgm/**").addPathPatterns("/video/upload","/video/upload","" +
                "/video/uploadCover","/video/userLike","/video/userUnLike","/video/saveComment")
        .excludePathPatterns("/user/queryPublisher");
        super.addInterceptors(registry);
    }

    @Bean(initMethod = "init")
    public ZKCuratorClient zkCuratorClient(){
        return new ZKCuratorClient();
    }


    @Bean
    public MiniIntercepto miniIntercepto(){
        return new MiniIntercepto();
    }
}
