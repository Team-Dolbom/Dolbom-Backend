package com.example.dolbom.domain.user.domain.repository;

import com.example.dolbom.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountId(String accountId);

    Optional<User> deleteUserByAccountId(String accountId);

}


