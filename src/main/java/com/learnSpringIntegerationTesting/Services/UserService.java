package com.learnSpringIntegerationTesting.Services;

import com.learnSpringIntegerationTesting.model.User;

import java.util.List;

public interface UserService {

    public User addUser(User user);

    public User update(int id, User user);

    public List<User> getAllUser();

    public User getUser(int id);

    public void deleteUser(int id);

}
