package com.learnSpringIntegerationTesting;

import com.learnSpringIntegerationTesting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<User, Integer> {
}
