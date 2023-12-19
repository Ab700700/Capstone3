package com.example.capstone3.Controller;

import com.example.capstone3.DTO.PassDTO;
import com.example.capstone3.Model.Company;
import com.example.capstone3.Model.Pass;
import com.example.capstone3.Service.PassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pass")
public class PassController {
    private final PassService passService;

    @GetMapping("/get")
    public ResponseEntity getPass(){
        return ResponseEntity.status(HttpStatus.OK).body(passService.getPasses());
    }
    @PostMapping("/add")
    public ResponseEntity addPass(@Valid @RequestBody PassDTO pass){
        passService.addPass(pass);
        return ResponseEntity.status(HttpStatus.OK).body("pass added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePass(@PathVariable Integer id, @Valid @RequestBody PassDTO pass){
        passService.updatePass(id,pass);
        return ResponseEntity.status(HttpStatus.OK).body("pass update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePass(@PathVariable Integer id){
        passService.deletePass(id);
        return ResponseEntity.status(HttpStatus.OK).body("pass delete");
    }
}

