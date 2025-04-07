package org.ai.backendrevisionproject.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {
    public static SecretKey key= Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(Authentication authentication){
        return Jwts
                .builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .claim("email",authentication.getName())
                .signWith(key)
                .compact();
    }

    public static String getEmailToken(String token){
        token=token.substring(7);
        Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        String email=String.valueOf(claims.get("email"));
        System.out.println(email);
        return  email;
    }

}
