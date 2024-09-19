package com.example.backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider {
    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    public String generateToken(Authentication auth){
        //auth object include: principal(UserDetails --> Represent the information of user include: name, password, authorities),
        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                //Đặt ngày tạo của token là thời điểm hiện tại.
                .setExpiration(new Date(new Date().getTime()+86400000))
                //Đặt ngày hết hạn của token là một ngày sau ngày tạo (86400000 ms = 24 giờ).
                .claim("email", auth.getName())
                //đại diện cho các thông tin hoặc dữ liệu được mã hóa trong token. Ở đây có nghĩa là đặt email username
                //là phần payload của jwt
                .signWith(key)
                .compact();
        return jwt;
    }

    public String getEmailFromToken(String jwt){

        jwt = jwt.substring(7);

        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claims.get("email"));

        return email;
    }
}
