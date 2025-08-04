package com.example.userservice.services;

import com.example.userservice.dto.auth.request.AuthenticationRequest;
import com.example.userservice.dto.auth.request.IntrospectRequest;
import com.example.userservice.dto.auth.response.AuthenticationResponse;
import com.example.userservice.dto.auth.response.IntrospectResponse;
import com.example.userservice.exception.AppException;
import com.example.userservice.exception.ErrorCode;
import com.example.userservice.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class AuthenticationService {

    @Resource
    private UserRepository userRepository;
    @Resource
    PasswordEncoder passwordEncoder;


    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SECRET_KEY;

    public AuthenticationResponse authentication(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        System.out.println("Request password: " + request.getPassword());
        System.out.println("DB hashed password: " + user.getPassword());
        System.out.println("Match result: " + passwordEncoder.matches(request.getPassword(), user.getPassword()));
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        System.out.println("Encoder class: " + passwordEncoder.getClass().getName());
        if (!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generalToken(request.getUsername());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }


    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        // lấy token
        var token = request.getToken();


        // giải mã thật toán đã has
        JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());

        // dùng để parse chuỗi token
        SignedJWT signedJWT = SignedJWT.parse(token);

        // kiểm tra token hết hạn chưa
        Date expTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);


        return IntrospectResponse.builder()
                .valid(verified && expTime.after(new Date()))
                .build();
    }


    private String generalToken(String username) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);


        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("Minh")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(
                        15, ChronoUnit.MINUTES
                ).toEpochMilli()))
                .claim("custom claim", "custom")
                .build();


        Payload payload = new Payload(jwtClaimsSet.toJSONObject());


        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        // MACSigner() khóa để kí và khóa giải mã trùng nhau
        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Can not create token");
            throw new RuntimeException(e);
        }

    }
}
