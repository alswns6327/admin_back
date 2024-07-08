package com.admin.config.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;

import java.security.Key;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public final static Duration ACCESS_TOKEN_EXPIRED = Duration.ofHours(2);
    public final static Duration REFRESH_TOKEN_EXPIRED = Duration.ofDays(14);

    public final String ACCESS_TOKEN = "access_token";
    public final String REFRESH_TOKEN = "refresh_token";

    // 토큰 생성
    public String generateToken(String adminId, Duration expiredAt){
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), adminId);
    }

    private String makeToken(Date expiry, String adminId) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 type : jwt
                .setIssuer(jwtProperties.getIssuer()) // 토큰 생성자
                .setExpiration(expiry) // 토큰 유효기간
                .setSubject(adminId) // 내용
                .claim("id", adminId) // 클레임 id
                // 서명
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) // 비밀키와 HS256으로 암호화
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
        return new UsernamePasswordAuthenticationToken(new User(claims.getSubject(), "", authorities), token, authorities);
    }

    // 토큰 유효성 검사
    public boolean validToken(String token) {
        try{
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String getAdminId(String token){
        Claims claims = getClaims(token);
        return claims.get("id", String.class);
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
