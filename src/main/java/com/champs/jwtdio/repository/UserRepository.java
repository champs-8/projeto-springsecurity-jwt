package com.champs.jwtdio.repository;

import com.champs.jwtdio.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<AppUser, Long> {
//    @Query("SELECT u FROM AppUser u JOIN FETCH u.roles WHERE u.username = ?1")
//    public AppUser findByUsername(String username);

    @Query("SELECT u FROM AppUser u JOIN FETCH u.roles WHERE u.username = (:username)")
    public AppUser findByUsername(@Param("username") String username);
}
