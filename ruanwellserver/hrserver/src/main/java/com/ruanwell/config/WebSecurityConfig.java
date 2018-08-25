package com.ruanwell.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruanwell.bean.Hr;
import com.ruanwell.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by robbinqin on 2018/4/8.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    HrService hrService;
    @Autowired
    CustomFilterSecurityMetadataSource customFilterSecurityMetadataSource;
    @Autowired
    CustomAccessDecisionManager customAfterInvocationManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        //1.过滤出权限
                        //2.进行权限认证
                        object.setSecurityMetadataSource(customFilterSecurityMetadataSource);
                        object.setAccessDecisionManager(customAfterInvocationManager);
                        return object;
                    }
                })
                .and().formLogin()
                .loginProcessingUrl("/login").usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        StringBuffer result = new StringBuffer();
                        result.append("{\"status\":\"success\",\"msg\":");
                        Hr hr = (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        ObjectMapper objectMapper = new ObjectMapper();
                        String s = objectMapper.writeValueAsString(hr);
                        result.append(s);
                        result.append("}");
                        out.write(result.toString());
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write("{\"status\":\"error\",\"msg\":\"登录失败!\"}");
                        out.flush();
                        out.close();
                    }
                })
                .permitAll().and().logout().permitAll().and().csrf().disable();
    }
}
