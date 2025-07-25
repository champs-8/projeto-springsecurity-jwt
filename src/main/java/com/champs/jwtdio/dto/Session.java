package com.champs.jwtdio.dto;

import lombok.Data;

@Data
public class Session {
    private String token;
    private String login;
}
