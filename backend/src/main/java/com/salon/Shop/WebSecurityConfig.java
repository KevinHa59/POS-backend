package com.salon.Shop;
import com.salon.Shop.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    CustomUserDetailsService customUserDetailsService;


    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/","/index","/register","/login").permitAll() // any kind of user can access these pages
                .anyRequest().authenticated() // other pages need authenticated
                .and()
                .formLogin().loginPage("/login") // redirect to this page if trying to access pages need authenticated without authenticated
                .defaultSuccessUrl("/login?success=true")// redirect to this page if login success
                .failureUrl("/login?success=false")// redirect to this page if login fail
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("email") // name of username in html form must be match this
                .passwordParameter("password") // name of password in html form must be match this
                .and().httpBasic();
        ;

    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
