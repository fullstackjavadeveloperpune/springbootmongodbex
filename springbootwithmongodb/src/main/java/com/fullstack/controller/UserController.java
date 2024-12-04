package com.fullstack.controller;

import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.model.User;
import com.fullstack.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        return ResponseEntity.ok(userService.signUp(user));
    }

    @PostMapping("/saveall")
    public ResponseEntity<List<User>> saveAll(@RequestBody List<User> userList) {
        return ResponseEntity.ok(userService.saveAll(userList));
    }

    @GetMapping("/signin/{userEmailId}/{userPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String userEmailId, @PathVariable String userPassword) {
        return ResponseEntity.ok(userService.signIn(userEmailId, userPassword));
    }


    @GetMapping("/findbyanyinput/{input}")
    public ResponseEntity<List<User>> findByAnyInput(@PathVariable String input) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return ResponseEntity.ok(userService.findAll().stream().filter(user -> user.getUserName().equals(input)
                || user.getUserEmailId().equals(input)
                || String.valueOf(user.getUserId()).equals(input)
                || String.valueOf(user.getUserContactNumber()).equals(input)
                || simpleDateFormat.format(user.getUserDOB()).equals(input)).toList());
    }

    @GetMapping("/findbyid/{userId}")
    public ResponseEntity<Optional> findById(@PathVariable int userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @GetMapping("/findbyname")
    public ResponseEntity<List<User>> findByName(@RequestParam String userName) {
        return ResponseEntity.ok(userService.findByName(userName));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> update(@PathVariable int userId, @RequestBody User user) {
        User user1 = userService.findById(userId).orElseThrow(() -> new RecordNotFoundException("User ID Does Not Exist"));

        user1.setUserName(user.getUserName());
        user1.setUserAddress(user.getUserAddress());
        user1.setUserDOB(user.getUserDOB());
        user1.setUserEmailId(user.getUserEmailId());
        user1.setUserPassword(user.getUserPassword());
        user1.setUserContactNumber(user.getUserContactNumber());

        return ResponseEntity.ok(userService.update(user1));
    }

    @DeleteMapping("/deletebyid/{userId}")
    public ResponseEntity<String> deleteById(@PathVariable int userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() {
        userService.deleteAll();

        return ResponseEntity.ok("All Data Deleted Successfully");
    }
}
