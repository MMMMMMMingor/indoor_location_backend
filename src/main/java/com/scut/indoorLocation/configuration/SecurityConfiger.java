package com.scut.indoorLocation.configuration;

import com.scut.indoorLocation.filter.JwtFilter;
import com.scut.indoorLocation.service.impl.JWTUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * 配置JWT安全框架
 * Created by Mingor on 2019/12/26 22:11
 */
@EnableWebSecurity
public class SecurityConfiger extends WebSecurityConfigurerAdapter {
    @Resource
    private JWTUserDetailsService jwtUserDetailsService;

    @Resource
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(jwtUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // 关掉csrf，不然老有个csrftoken传上来；然后auth路径无权限，其他路径全都要验证;关掉session
        http.csrf().disable()
                .authorizeRequests().antMatchers("/auth")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 把jwt过滤器加到，用户验证的过滤器前面。这样在进入用户验证过滤器前，上下文就准备好了
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}