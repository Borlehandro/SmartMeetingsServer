package com.smartmeetings.server.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query(value = "select user_entity.name from users user_entity")
    List<String> findAllNames();
    Optional<User> findByEmail(String email);
    Optional<User> findOneByToken(String token);
    boolean existsUserByEmailAndToken(String email, String token);
}