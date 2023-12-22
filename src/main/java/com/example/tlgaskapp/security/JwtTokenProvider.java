package com.example.tlgaskapp.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class JwtTokenProvider { //generate JWT
    @Value("$tlgapp.app.secret")
    private String APP_SECRET; //key
    @Value("$tlgapp.expires.in")
    private long EXPIRES_IN;   //time



    public String generateJwtToken(Authentication authentication){ //token build ediyoruz
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal(); //aut. olacak user'ı cascae ediyoruz.
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN); // ne zaman expire olacak?
        return Jwts.builder().setSubject(Long.toString(userDetails.getId())).setIssuedAt(new Date()).setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();

    }
    Long getUserIdFromJwt(String token){ //token çözmek için
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject()); //şifre çözüldü, userid alındı.
    }

}
