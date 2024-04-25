package com.turkcell.identityService.core.filters;

import com.turkcell.identityService.core.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component //extends ettiği alan gibi çalışabilmesi için
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private UserDetailsService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String jwtHeader = request.getHeader("Authorization ");
        if (jwtHeader != null && jwtHeader.startsWith("Bearer")) {
            String jwt = jwtHeader.substring(7);
            String username = jwtService.extractUser(jwt);
            if (username != null) {
                UserDetails user = userService.loadUserByUsername(username);
                if (jwtService.validateToken(jwt, user)) {
                    //Boilerplate
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    //kullanıcı sisteme hiç girmemişse
                }
            }
        }
        filterChain.doFilter(request, response);

    }
}