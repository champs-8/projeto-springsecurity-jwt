package com.champs.jwtdio.service;

import com.champs.jwtdio.model.AppUser;
import com.champs.jwtdio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public void createUser(AppUser user){
        String pass = user.getPassword();
        user.setPassword(encoder.encode(pass));
//        // Set default role (e.g., "USER")
//        user.setRoles(List.of("USER"));

        // Save the user to the repository
        repository.save(user);
    }
}
