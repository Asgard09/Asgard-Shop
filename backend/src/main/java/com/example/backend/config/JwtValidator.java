package com.example.backend.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtValidator extends OncePerRequestFilter {
    //Cung cấp cơ chế đảm bảo rằng bộ lọc chỉ được thực thi một lần cho mỗi yêu cầu
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    //Nơi định nghĩa logic của bộ lọc
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        if(jwt!= null){

            jwt = jwt.substring(7);
            //loại bỏ phần bearer
            try{
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
                //parserBuilder() --> phân tích cú pháp jwt
                //setSigningKey(key) --> thiết lập khóa bí mật được sử dụng để xác minh chữ ký của JWT
                //parseClaimsJws(jwt) --> nó sẽ giải mã phần payload (các claims) và trả về một đối tượng Jws<Claims>
                String email = String.valueOf(claims.get("email"));

                String authorities = String.valueOf(claims.get("authorities"));

                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                //Chuyển đổi chuỗi các quyền phân tách bằng dấu phẩy thành danh sách các đối tượng GrantedAuthority

                Authentication authentication = new UsernamePasswordAuthenticationToken(email,null,auths);
                //Tạo authentication object ( bao gồm username, password, role)

                SecurityContextHolder.getContext().setAuthentication(authentication);
                //Đặt đối tượng Authentication vào SecurityContextHolder. Điều này cho phép Spring Security biết người dùng đã được xác thực và có quyền gì trong suốt quá trình xử lý yêu cầu.
            }catch (Exception e){
                throw new BadCredentialsException("invalid token... from jwt validation");

            }
        }
        filterChain.doFilter(request, response);
    }





}
