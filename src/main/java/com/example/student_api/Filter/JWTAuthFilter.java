package com.example.student_api.Filter;

import com.example.student_api.Util.JWTUtil;
import com.example.student_api.service.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;

    public JWTAuthFilter(JWTUtil jwtUtil, CustomUserDetailService customUserDetailService){
        this.jwtUtil = jwtUtil;
        this.customUserDetailService = customUserDetailService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String authHeader = request.getHeader("Authorization");
       String token = null;
       String username = null;
       if(authHeader!=null && authHeader.startsWith("Bearer ")){
           token = authHeader.substring(7);
           username = jwtUtil.extractUsername(token);
       }
       if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
           UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
           if(jwtUtil.validateToken(username, userDetails, token)){
               UsernamePasswordAuthenticationToken authToken =  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authToken);
           }
       }
       filterChain.doFilter(request,response);

    }
}
