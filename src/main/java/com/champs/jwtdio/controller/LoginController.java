package com.champs.jwtdio.controller;

import com.champs.jwtdio.dto.Login;
import com.champs.jwtdio.dto.Session;
import com.champs.jwtdio.model.AppUser;
import com.champs.jwtdio.repository.UserRepository;
import com.champs.jwtdio.security.JWTCreator;
import com.champs.jwtdio.security.JWTObject;
import com.champs.jwtdio.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserRepository repository;


    @PostMapping("/login")
    public Session toLog(@RequestBody Login login){
        AppUser user = repository.findByUsername(login.getUsername());
        if(user != null) {
            boolean passwordOK = encoder.matches(login.getPassword(), user.getPassword());
            if(!passwordOK){
                throw new RuntimeException("Invalid password for login: " + login.getUsername());
            }
            // Sending a object Session for to return more infos of the user
            Session session = new Session();
            session.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date());
            jwtObject.setExpiration(new Date(System.currentTimeMillis() + securityConfig.getExpiration())); // 1 hour
            jwtObject.setRoles(String.valueOf(user.getRoles()));
            session.setToken(JWTCreator.createToken(securityConfig.getPrefix(), securityConfig.getKey(), jwtObject));
            return session;
        }else{
            throw new RuntimeException("User not found for login: " + login.getUsername());
        }
    }
}
