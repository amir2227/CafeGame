package com.cafe.game.security.jwt;

import com.cafe.game.user.model.User;
import com.cafe.game.user.repository.UserRepository;
import com.cafe.game.utils.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter { // OncePerRequestFilter fire the class on every request
    private final JwtUtils jwtUtils;
    private final UserRepository repository;
    private final RedisTemplate<String,Object> cacheService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        if (request.getMethod().equals("OPTIONS")){
            response.setStatus(204);
            return;
        }

        final String authHeader = request.getHeader("Authorization");

         if(authHeader == null || !authHeader.startsWith("Bearer ")){
             if (request.getRequestURI().equals("/api/v1/auth/refresh")){
                 response.setStatus(HttpStatus.UNAUTHORIZED.value());
                 response.getWriter().write("UnAuthorized");
                 return;
             }
             filterChain.doFilter(request,response);
             return;
         }
        final String jwt;
        jwt = authHeader.substring(7);
        if (request.getRequestURI().equals("/api/v1/auth/refresh")){
            try {
                final String username = jwtUtils.extractUsername(jwt,true);
                final String jti = jwtUtils.extractJti(jwt,true);
                if (jti != null) {
                    if (isExistInRevokeList(jti)) {
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        response.getWriter().write("UnAuthorized");
                        return;
                    }
                }
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    User userDetails = this.repository.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("user not found!"));
                    if (!jwtUtils.validateToken(jwt, true, userDetails)) {
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        response.getWriter().write("UnAuthorized");
                        return;
                    }
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
                filterChain.doFilter(request,response);
                return;
            }catch (Exception e){
                log.error("invalid refresh token: {}",e.getMessage());
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("UnAuthorized");
                return;
            }
        }
        try {
            final String userNationalId = jwtUtils.extractUsername(jwt,false);
            final String jti = jwtUtils.extractJti(jwt,false);
            if (jti != null){
                if (isExistInRevokeList(jti)){
                    log.error("revoked token!");
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.getWriter().write("UnAuthorized");
                    return;
                }
            }
            // when authentication is null means that the user is not yet authenticated
            if (userNationalId != null && SecurityContextHolder.getContext().getAuthentication() == null){
                User userDetails = this.repository.findByUsername(userNationalId)
                        .orElseThrow(()-> new UsernameNotFoundException("user not found!"));

                if (jwtUtils.validateToken(jwt,false,userDetails)){
                    // update security context
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            jti,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }catch (Exception e){
            log.error("invalid token: {}",e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("UnAuthorized");
            return;
        }
         filterChain.doFilter(request,response);

    }

    public Boolean isExistInRevokeList(String jti){
        return cacheService.opsForSet().isMember(Constants.REVOKE_LIST_PREFIX.getStr(),jti);
    }
}
