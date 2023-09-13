package com.learnSpringIntegerationTesting.Repo;

import com.learnSpringIntegerationTesting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
