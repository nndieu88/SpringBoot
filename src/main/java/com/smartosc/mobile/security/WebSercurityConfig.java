package com.smartosc.mobile.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSercurityConfig<CustomUserDetailService> extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoit jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtUserDetailServce jwtUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/logins").permitAll() // Api đăng nhập đăng kí không cần kiểm tra xác thực
//                .antMatchers(HttpMethod.POST, "/admins/users").permitAll() // Api đăng nhập đăng kí không cần kiểm tra xác thực
//                .antMatchers(HttpMethod.GET,  "/users").permitAll() // Api đăng nhập đăng kí không cần kiểm tra xác thực
                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Không sử dụng session lưu lại trạng thái của principal
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Lớp lọc jwt token sẽ được thực thi trước các lớp lọc mặc định
    }

}
