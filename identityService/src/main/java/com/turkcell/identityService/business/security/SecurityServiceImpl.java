package com.turkcell.identityService.business.security;

import com.turkcell.identityService.business.constants.Roles;
import com.turkcell.identityService.core.services.SecurityService;
import com.turkcell.identityService.entitites.Role;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService
{
    private static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "api/v1/auth/refresh",
            "/api/v1/auth/**",
            "/api/v1/users"
    };
    // C# => Extension Method Alternatif
    @Override
    public HttpSecurity configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(x-> x
                .requestMatchers(WHITE_LIST_URLS).permitAll()
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers(HttpMethod.POST, "customerservice/api/v1/**").hasAnyAuthority(Roles.ADMIN)
                .requestMatchers(HttpMethod.PUT, "customerservice/api/v1/**").hasAnyAuthority(Roles.ADMIN)
                .requestMatchers(HttpMethod.DELETE, "customerservice/api/v1/**").hasAnyAuthority(Roles.ADMIN)
                .requestMatchers(HttpMethod.GET, "customerservice/api/v1/**").hasAnyAuthority(Roles.ADMIN)
                .anyRequest().authenticated()
        );
        return http;
    }
}
