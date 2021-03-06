package ru.lavrov.spring_Sec_Bootstrap_JS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lavrov.spring_Sec_Bootstrap_JS.model.User;
import ru.lavrov.spring_Sec_Bootstrap_JS.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/restadmin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adminpage")
    public ResponseEntity<List<User>> userList() {
        final List<User> users = userService.findAll();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/adminpage/new")
    public List<User> addUser(@RequestBody User user) {
        userService.saveUser(user);
        return userService.findAll();
    }

    @PutMapping("/adminpage/edit")
    public ResponseEntity<?> update(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/adminpage/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable Long id) {
        userService.deleteById(userService.getUserById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
