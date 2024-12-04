package com.fullstack.repository;

import com.fullstack.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    List<User> findByUserName(String userName);

    User findByUserEmailIdAndUserPassword(String userEmailId, String userPassword);
}
