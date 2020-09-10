package org.example.sweater.config;

import org.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//ctrl + alt + o - убрать все ненужные импорты

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//при старте приложения конфигурирует websecurity
    @Autowired
    private UserService userService;
    @Override // потом система заходит сюда
    protected void configure(HttpSecurity http) throws Exception {//передает на вход объект
        http
                //включаем авторизацию
                .authorizeRequests()
                    //для главной страницы полный доступ
                    .antMatchers("/", "/registration").permitAll()
                    //для всех остальных включаем авторизацию
                    .anyRequest().authenticated()
                .and()
                    //включаем форму логина
                    .formLogin()
                    //страница логина находится на этом мапинге
                    .loginPage("/login")
                    //разришаем всем этим пользоваться
                    .permitAll()
                .and()
                    //включаем разлогиневание
                    .logout()
                    //разришаем всем этим пользоваться
                    .permitAll();
        }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

    }
}