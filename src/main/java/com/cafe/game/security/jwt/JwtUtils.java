package com.cafe.game.security.jwt;

import com.cafe.game.user.model.User;
import com.cafe.game.user.payload.response.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;


@Component
@Slf4j
public class JwtUtils {

    @Value("${app.secret.key}")
    private String SECRET_KEY;

    @Value("${app.secret.refresh.key}")
    private String REFRESH_SECRET_KEY;

    @Value("${app.secret.key.expirationInMili}")
    private Long jwtExpireInMili;
    @Value("${app.secret.key.refereshExpirationInMili}")
    private Long jwtRefreshExpireInMili;

    public String extractUsername(String token,Boolean isRefresh){
        return extractAllClaims(token,
                isRefresh?REFRESH_SECRET_KEY:SECRET_KEY).get("nationalId").toString();
    }
    public String extractJti(String token,Boolean isRefresh){
        return extractAllClaims(token,
                isRefresh?REFRESH_SECRET_KEY:SECRET_KEY).get("jti").toString();
    }
    public Date extractExpiration(String token,String key) {
        return extractClaim(token,key, Claims::getExpiration);
    }

    public <T> T extractClaim(String token,String key, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token,key);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, String key) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey(key))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey(String key) {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    private Boolean isTokenExpired(String token,String key) {
        return extractExpiration(token,key).before(new Date());
    }
    public Boolean validateToken(String token,Boolean isRefresh, User userDetails) {
        final String username = extractUsername(token,isRefresh);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token,isRefresh ? REFRESH_SECRET_KEY:SECRET_KEY));
    }

    public AuthResponse createToken(User user){
        Claims claims = createClaims(user);
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + jwtExpireInMili);
        Date refreshTokenValidity = new Date(tokenCreateTime.getTime() + jwtRefreshExpireInMili);
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .setIssuer("ngn-net.net")
                .signWith(getSigninKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
        String refresh = Jwts.builder()
                .setClaims(claims)
                .setExpiration(refreshTokenValidity)
                .setIssuer("ngn-net.net")
                .signWith(getSigninKey(REFRESH_SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(refresh)

//                .user(ResponseMapper.map(user))
                .build();
    }
    private Claims createClaims(User user){
        Claims claims = Jwts.claims().setSubject(String.valueOf(user.getId()));
        claims.put("username",user.getUsername());
        claims.put("phone",user.getPhone());
        claims.put("email",user.getPhone());
        claims.put("isActive", user.getIsActive());
        claims.put("jti", UUID.randomUUID());
        return claims;
    }
}
