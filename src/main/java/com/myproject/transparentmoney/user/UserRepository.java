package com.myproject.transparentmoney.user;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.transparentmoney.user.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByUsername(String username);
}
