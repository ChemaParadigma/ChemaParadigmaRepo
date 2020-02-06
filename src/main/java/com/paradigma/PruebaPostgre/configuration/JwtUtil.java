package com.paradigma.PruebaPostgre.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

public class JwtUtil {

    /**
     * Metodo para crear el JWT y enviarlo al cliente en el header de la respuesta
     */

    public static void addAuthentication(HttpServletResponse res, String userName) {

        // Añadimos el hash con el que firmamos

        String token = Jwts.builder()
                .setSubject(userName)
                .signWith(SignatureAlgorithm.HS512, "PruebasChema")
                .compact();

        // agreamos el encabezado al token
        res.addHeader("Authorization", "Bearer " + token);
    }


    /**
     * Metodo para validar el token enviado por el cliente
     */

    public static Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        return Optional.ofNullable(token)
                .map(JwtUtil::decodeToken)
                .filter(Objects::nonNull)
                .map(userResult -> new UsernamePasswordAuthenticationToken(userResult, null, Collections.emptyList()))
                .orElse(null);
    }

    private static String decodeToken(String tokenResult) {

        return Jwts.parser()
                .setSigningKey("PruebasChema")
                .parseClaimsJws(tokenResult.replace("Bearer ", " ")) // Metodo que valida
                .getBody()
                .getSubject();
    }
}
