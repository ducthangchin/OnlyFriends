package com.ducthangchin.configuration;

import com.ducthangchin.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private WebUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(
                        "/register",
                        "/about",
                        "/confirmregister",
                        "/invaliduser",
                        "/expiredtoken",
                        "/verify")
                .permitAll()
                .antMatchers(
                        "/js/*",
                        "/css/*",
                        "/img/**")
                .permitAll()
                .antMatchers(
                        "/addstatus",
                        "/editstatus",
                        "/mystatus",
                        "/add-img-to-status/*",
                        "/delete-status-img/")
                .hasRole("USER")
                .antMatchers(
                        "/user-management",
                        "/changeauth",
                        "/changerole")
                .hasRole("ADMIN")
                .antMatchers("/profile",
                        "/profile/*",
                        "/editprofile",
                        "/deletestatus",
                        "/home",
                        "/",
                        "/viewstatus",
                        "/upload-profile-photo",
                        "/addComment",
                        "/deletecomment")
                .authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/profile")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);

    }
}