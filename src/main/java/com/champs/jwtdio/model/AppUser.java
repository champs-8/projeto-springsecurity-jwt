package com.champs.jwtdio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tab_user")
@Entity
public class AppUser {
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 20)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(name = "role_id")
    @ElementCollection(fetch = FetchType.EAGER) // EAGER to load roles immediately
    @CollectionTable(name = "tab_user_roles", joinColumns = @JoinColumn(name = "id_user"))
    private List<String> roles = new ArrayList<>();
}
