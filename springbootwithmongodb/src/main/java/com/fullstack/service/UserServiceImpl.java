package com.fullstack.service;

import com.fullstack.model.User;
import com.fullstack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signUp(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean signIn(String userEmailId, String userPassword) {

        boolean flag = false;

        User user = userRepository.findByUserEmailIdAndUserPassword(userEmailId, userPassword);

        if (user != null && user.getUserEmailId().equals(userEmailId) && user.getUserPassword().equals(userPassword)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> findByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public List<User> saveAll(List<User> userList) {
        return userRepository.saveAll(userList);
    }
}
