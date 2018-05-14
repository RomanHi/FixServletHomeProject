package ru.rikabc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.rikabc.interceptors.TokenManager;

/**
 * @Author Roman Khayrullin on 20.04.2018
 * @Version 1.0
 */
@Configuration
public class SpringMVCConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenManager)
                .addPathPatterns("/profile/**")
                .excludePathPatterns("/logout");

    }

}
