package com.studentDemo.config;

import com.studentDemo.service.JwtService;
import com.studentDemo.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Bearer eys28r2fkw4r82380nka823y082rbj32llj2l2qnw8328238jev;
        String authHeader = request.getHeader("Authorization");
        String token=null;
        String username=null;
        // check if the header is not null and starts with "Bearer "
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token =authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        // check if the username is not null and the security context is null
        // check if user already logged in
        if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){

            // applicationContext is used to get the bean of MyUserDetailsService (UserDetails)
            UserDetails userDetails =context.getBean(MyUserDetailsService.class).loadUserByUsername(username);

            if(jwtService.validateToken(token,userDetails)){
                // create an authentication token
                UsernamePasswordAuthenticationToken authtoken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // set the security context
                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
