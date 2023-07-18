package com.travelexpensemgmt.userservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {

        try {
            final List<User> users = userService.getUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        try {
            final User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Methode delete a User by Id
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<User> deleteUserkbyId(@PathVariable("userId") String taskId) {
        try {
            User task = userService.deleteUserById(taskId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Method update the users attributes
    @PutMapping
    public ResponseEntity<User> updateById(@RequestBody User user) {
        try {
            User updatedUser = userService.updateById(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

