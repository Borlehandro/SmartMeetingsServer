package com.smartmeetings.server.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query(value = "select user_entity.telegramUsername from users user_entity")
    List<String> findAllNames();
}