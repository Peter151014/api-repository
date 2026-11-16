package com.example.graphql.repository;

import com.example.graphql.Entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean findByEmail(String email);

    User findUserByEmail(String user);

    User findUserById(Long id);

    boolean existsByEmail(@NotBlank(message = "Email is required") @Email(message = "Enter a valid email address") String email);
}
