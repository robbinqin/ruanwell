package com.ruanwell.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by robbinqin on 2018/4/10.
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    /**
     * 在decide方法中执行认证操作
     * @param authentication  当前认证信息
     * @param configAttributes  表示角色列表
     * @return 返回null表示请求通过，可以执行，如果请求未通过，请抛异常
     * @throws AccessDeniedException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute next = iterator.next();
            String attribute = next.getAttribute();
            //如果是登录即可访问，那么去判断是否登录即可
            if ("ROLE_LOGIN".equals(attribute)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    //没登录
                    throw new BadCredentialsException("未登录!");
                }
                return;
            }
            //获取当前登录用户具备哪些角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(attribute)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
