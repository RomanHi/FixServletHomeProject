package ru.rikabc.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.rikabc.security.handlers.SuccessUrlHandler;

/**
 * @Author Roman Khayrullin on 26.04.2018
 * @Version 1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserDetailsService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/index").permitAll()
                    .antMatchers("/signUp").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/fonts/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/index").permitAll()
                    .successHandler(new SuccessUrlHandler())
                .and()
                .logout()
                    .logoutUrl("/logout").deleteCookies("JSESSIONID","Authorization")
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(encoder);
    }
}
