package com.champs.jwtdio.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

public class JWTCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

        public static String createToken(String prefix, Key key, JWTObject jwtObject) {
            String token = Jwts.builder().setSubject(jwtObject.getSubject())
                    .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles()))
                    .setIssuedAt(jwtObject.getIssuedAt())
                    .setExpiration(jwtObject.getExpiration())
                    .signWith(key, SignatureAlgorithm.HS512)
                    .compact();
            return prefix + " " + token;
        }

        public static JWTObject create(String token, String key, String prefix)
        throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
            JWTObject jwtObject = new JWTObject();
            var claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token.replace(prefix + " ", ""))
                    .getBody();

            jwtObject.setSubject(claims.getSubject());
            jwtObject.setIssuedAt(claims.getIssuedAt());
            jwtObject.setExpiration(claims.getExpiration());
            jwtObject.setRoles((String[]) claims.get(ROLES_AUTHORITIES, String[].class));

            return jwtObject;
        }

        private static List<String> checkRoles(List<String> roles) {
            return roles.stream()
                    .map(s -> "ROLE_".concat(s.replace("ROLE_", "")))
                    .collect(Collectors.toList());
        }
}
