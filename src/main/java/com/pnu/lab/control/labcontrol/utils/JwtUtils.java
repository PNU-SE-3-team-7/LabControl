package com.pnu.lab.control.labcontrol.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pnu.lab.control.labcontrol.domain.User;
import com.pnu.lab.control.labcontrol.exception.ValidationException;
import com.pnu.lab.control.labcontrol.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import static com.pnu.lab.control.labcontrol.utils.UserUtils.ROLE_PREFIX;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final UserService userService;

    @Value("${security.secret}")
    private String jwtSecret;
    @Value("${security.lifetime}")
    private int lifeTime;
    @Value("${security.starts}")
    private String tokenStarts;

    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(lifeTime))
                .sign(getSignKey());
    }

    public void authenticateUser(String header) {
        DecodedJWT decodedJWT = verifyToken(header);
        if (Objects.isNull(decodedJWT)) {
            return;
        }

        String email = decodedJWT.getSubject();
        User user = userService.getByEmail(email)
                .orElseThrow(() -> new ValidationException("Cannot find User."));

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole()));
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(user, null, authorities));
    }

    public DecodedJWT verifyToken(String header) {
        if (StringUtils.isBlank(header)) {
            return null;
        }
        if (!StringUtils.startsWith(header, tokenStarts)) {
            throw new ValidationException("Header value is not valid.");
        }

        String token = StringUtils.substring(header, tokenStarts.length()).trim();
        JWTVerifier verifier = JWT.require(getSignKey()).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        if (decodedJWT.getExpiresAtAsInstant().isBefore(Instant.now())) {
            throw new ValidationException("Token expired.");
        }

        return decodedJWT;
    }

    private Algorithm getSignKey() {
        return Algorithm.HMAC256(jwtSecret.getBytes());
    }
}
