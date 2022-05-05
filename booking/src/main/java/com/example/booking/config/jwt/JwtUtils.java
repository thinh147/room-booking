package com.example.booking.config.jwt;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtils {

    private final static Logger Logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${app.jwtSecret}")
    private  String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e){
            Logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            Logger.error("Invalid JWT Token: {}", e.getMessage());
        } catch (ExpiredJwtException e){
            Logger.error("Invalid JWT expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e){
            Logger.error("Invalid JWT unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e){
            Logger.error("JWT claims string empty : {}", e.getMessage());
        }
        return false;
    }

}
