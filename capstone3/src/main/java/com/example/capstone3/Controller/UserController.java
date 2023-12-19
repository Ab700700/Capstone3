package com.example.capstone3.Controller;

import com.example.capstone3.Model.User;
import com.example.capstone3.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("user added");
    }
    @PutMapping("/update/{id},{password}")
    public ResponseEntity updateUser(@PathVariable Integer id,@PathVariable String password, @Valid @RequestBody User user){
        userService.updateUser(id,user,password);
        return ResponseEntity.status(HttpStatus.OK).body("user update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("user delete");
    }

}
