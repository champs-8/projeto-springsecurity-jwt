package com.champs.jwtdio.security;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Data
@Component
public class JWTObject {
    private String subject; //nome do usuário
    private String issuedAt; //data de emissão
    private String expiration; //data de expiração
    private List<String> roles; //lista de roles do usuário

    public void setRoles(String ... roles){
        this.roles = Arrays.asList(roles);
    }
}
