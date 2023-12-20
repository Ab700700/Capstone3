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
    @PutMapping("/update/{id}/{password}")
    public ResponseEntity updateUser(@PathVariable Integer id,@PathVariable String password, @Valid @RequestBody User user){
        userService.updateUser(id,user,password);
        return ResponseEntity.status(HttpStatus.OK).body("user update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("user delete");
    }
    @PutMapping("/{user_id}/assign/{pass_id}")
    public ResponseEntity assignUserToPass(@PathVariable Integer user_id,@PathVariable Integer pass_id){
        userService.assignUserToPass(user_id, pass_id);
        return ResponseEntity.status(HttpStatus.OK).body("assign done");
    }
    @PutMapping("/activateCompany/{user_id}/{company_id}")
    public ResponseEntity ActivateCompany(@PathVariable Integer user_id ,@PathVariable Integer company_id){
        userService.ActivateCompany(user_id, company_id);
        return ResponseEntity.status(HttpStatus.OK).body("company activated");
    }
    @PutMapping("/activateBusiness/{user_id}/{business_id}")
    public ResponseEntity ActivateBusiness(@PathVariable Integer user_id ,@PathVariable Integer business_id){
        userService.ActivateBusiness(user_id, business_id);
        return ResponseEntity.status(HttpStatus.OK).body("business activated");
    }


}
