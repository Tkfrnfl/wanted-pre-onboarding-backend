package com.example.wanted.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // HTTP 요청에서 Authorization헤더를 찾아 토큰 반환
        String token = JwtTokenProvider.resolveToken(request);

        // 토큰이 있다면 진행
        if(token != null) {
            // 토큰 유효성 검사 -> 만료,위조 등 유효하지 않음
            if(!jwtTokenProvider.validateToken(token)){

                response.getWriter().write("인증이 유효하지 않습니다");
                return;
            }
            // 토큰 유효 -> getUserInfoFromToken메서드를 사용해 JWT 토큰의 payload에서 정보 반환
            Claims info = jwtTokenProvider.getUserInfoFromToken(token);    //토큰에서 user정보 가져옴(payload)

            Authentication authentication = getAuth(info);


            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request,response);


    }

    // Request Header 에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    public Authentication getAuth(Claims claims){

        if(claims.get("email")==null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("email").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.get("email").toString(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

}
