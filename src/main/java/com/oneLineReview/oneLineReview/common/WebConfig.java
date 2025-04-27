package com.oneLineReview.oneLineReview.common;

import com.oneLineReview.oneLineReview.common.interceptor.LoginInterceptor;
import com.oneLineReview.oneLineReview.common.interceptor.OwnershipInterceptor;
import com.oneLineReview.oneLineReview.common.resolver.UserIdArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration

public class WebConfig implements WebMvcConfigurer {

    private final UserIdArgumentResolver loginArgumentResolver;
    private final OwnershipInterceptor reviewOwnershipInterceptor;

    public WebConfig(UserIdArgumentResolver loginArgumentResolver, OwnershipInterceptor reviewOwnershipInterceptor){
        this.loginArgumentResolver = loginArgumentResolver;
        this.reviewOwnershipInterceptor = reviewOwnershipInterceptor;
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/auth/login/**",
                        "/auth/join/**",
                        "/review/detail/**",
                        "/review/list",
                        "/comment/{reviewId}",
                        "/book/**",
                        "/",
                        "/css/**",
                        "/js/**",
                        "/image/**",
                        "/error",
                        "/error-page/**",
                        "/favicon.ico",
                        "/review/bookInfo/**"
                );
        registry.addInterceptor(reviewOwnershipInterceptor)
                .order(2)
                .addPathPatterns("/review/**")
                .addPathPatterns("/comment/**");

    }

}
