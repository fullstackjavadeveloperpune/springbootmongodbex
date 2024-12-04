package com.fullstack.service;

import com.fullstack.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User signUp(User user);

    boolean signIn(String userEmailId, String userPassword);

    Optional<User> findById(int userId);

    List<User> findByName(String userName);

    List<User> findAll();

    User update(User user);

    void deleteById(int userId);

    void deleteAll();

    List<User> saveAll(List<User> userList);
}
