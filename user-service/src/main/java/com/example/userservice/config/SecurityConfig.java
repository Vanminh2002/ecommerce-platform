package com.example.userservice.config;

import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SECRET_KEY;

    private final String[] PUBLIC_ENDPOINT = {
            "/api/v1/auth/**",
            "/**",

    };

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(PUBLIC_ENDPOINT).permitAll().anyRequest().authenticated()
                );
        http.oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
//                        dùng khi sử dụng api của bên thứ 3
//                        jwt.jwkSetUri()
                                        jwt.decoder(jwtDecoder())

                        )
        );
        return http.build();
    }


    @Bean
    JwtDecoder jwtDecoder() {

        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "HS512");

        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();

    }


}
