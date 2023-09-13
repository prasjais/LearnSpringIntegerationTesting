package com.learnSpringIntegerationTesting.Controller;

import com.learnSpringIntegerationTesting.Services.UserService;
import com.learnSpringIntegerationTesting.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user)
    {
        User user1 = this.userService.addUser(user);
        return new ResponseEntity<User>(user1, HttpStatus.ACCEPTED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user)
    {
        User user1 = this.userService.update(id, user);
        return new ResponseEntity<User>(user1, HttpStatus.FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUser()
    {
        List<User> userList = this.userService.getAllUser();
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id)
    {
        User user = this.userService.getUser(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Integer id)
    {
        this.userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.FOUND);
    }

}
