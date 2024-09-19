package com.example.backend.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session ->
                        session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(Authorize ->
                        Authorize
                                .requestMatchers("/api/**").authenticated()
                                //Nếu request vào URL bắt đầu bằng /api/**, yêu cầu đó sẽ cần phải được xác thực (authenticated), nghĩa là phải có token JWT hợp lệ
                                .anyRequest().permitAll()
                                //Với các URL khác, chẳng hạn như trang web tĩnh hoặc tài nguyên công cộng, request sẽ được cho phép mà không cần xác thực (permitAll())
                )
                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                //Bảo vệ khỏi cross-side
                .cors(cors ->
                //CORS là viết tắt của Cross-Origin Resource Sharing là một cơ chế cho phép nhiều tài nguyên khác nhau (fonts, Javascript, v.v…) của một trang web có thể được truy vấn từ domain khác với domain của trang đó.
                        cors.configurationSource(new CorsConfigurationSource() {
                            @Override
                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                                CorsConfiguration cfg = new CorsConfiguration();
                                cfg.setAllowedOrigins(Arrays.asList(
                                        "http://localhost:3000",
                                        "http://localhost:4200"
                                        //Origin
                                ));
                                //Chỉ những request từ 2 tên miền này mới được phép truy cập
                                cfg.setAllowedMethods(Collections.singletonList("*"));
                                cfg.setAllowCredentials(true);
                                //Cho phép gửi cookie và thông tin xác thực (như header Authorization) với yêu cầu cross-origin.
                                cfg.setAllowedHeaders(Collections.singletonList("*"));
                                cfg.setExposedHeaders(Arrays.asList("Authorization"));
                                //Xác định các headers mà client có thể đọc từ phản hồi (response) của yêu cầu cross-origin.
                                cfg.setMaxAge(3600L);
                                return cfg;
                            }
                        })
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
