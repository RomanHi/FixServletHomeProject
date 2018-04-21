package ru.rikabc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.rikabc.interceptors.SessionManager;

/**
 * @Author Roman Khayrullin on 20.04.2018
 * @Version 1.0
 */
@Configuration
public class SpringMVCConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    SessionManager getSessionManager() {
        return new SessionManager();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSessionManager())
                .addPathPatterns("/**")
                .excludePathPatterns("/resources/static/*", "/index","/signUp","/logout");

    }

}
