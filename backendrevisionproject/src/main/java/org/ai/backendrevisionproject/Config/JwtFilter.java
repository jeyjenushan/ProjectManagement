package org.ai.backendrevisionproject.Config;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.ai.backendrevisionproject.Config.JwtConstant.JWT_SECRET;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwtToken=request.getHeader("Authorization");
        if (jwtToken!=null && jwtToken.startsWith("Bearer ") ){
            jwtToken=jwtToken.substring(7);

            try {
                SecretKey key= Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key) // Set the signing key
                        .build()
                        .parseClaimsJws(jwtToken) // Parse the token and verify its signature
                        .getBody();


                String email=String.valueOf(claims.get("email"));

                String authorities=String.valueOf(claims.get("authorities"));
                List<GrantedAuthority>auths= AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                var authentication=new UsernamePasswordAuthenticationToken(email,null,auths);
                SecurityContextHolder.getContext().setAuthentication(authentication);


            }catch (Exception e){
                throw new BadCredentialsException("Invalid token");
            }

        }
        filterChain.doFilter(request,response);


    }
}
