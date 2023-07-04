package com.example.dolbom.global.security.jwt;

import com.example.dolbom.domain.auth.domain.RefreshToken;
import com.example.dolbom.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.dolbom.global.exception.ExpiredJwtException;
import com.example.dolbom.global.exception.InvalidJwtException;
import com.example.dolbom.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    public String generateAccessToken(String id){
        return generateToken(id, "access", jwtProperties.getAccessExp());
    }

    public String generateRefreshToken(String id) {
        String refresh = generateToken(id, "refresh", jwtProperties.getRefreshExp());

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .accountId(id)
                        .token(refresh)
                        .timeToLive(jwtProperties.getRefreshExp())
                        .build()
        );
        return refresh;
    }

    public String generateToken(String id, String type, Long exp){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setSubject(id)
                .claim("type", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String parseToken(String bearerToken){
        if(bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())){
            return bearerToken.replace(jwtProperties.getPrefix(), "");
        }
        return null;
    }

    public String resolveToken(HttpServletRequest request){
        String bearer = request.getHeader(jwtProperties.getHeader());
        return parseToken(bearer);
    }

    public Claims getTokenBody(String token){
        try{
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token).getBody();
        } catch (io.jsonwebtoken.ExpiredJwtException e){
            throw ExpiredJwtException.EXCEPTION;
        } catch (Exception e){
            throw InvalidJwtException.EXCEPTION;
        }
    }

    public String getTokenSubject(String token){
        return getTokenBody(token).getSubject();
    }

    public Authentication authentication(String token){
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
