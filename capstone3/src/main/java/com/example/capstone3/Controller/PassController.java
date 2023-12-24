package com.example.capstone3.Controller;

import com.example.capstone3.DTO.PassDTO;
import com.example.capstone3.Model.Company;
import com.example.capstone3.Model.Pass;
import com.example.capstone3.Service.PassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

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

    //get all passes by user id
    @GetMapping("/getByUser/{id}")
    public ResponseEntity getPassByUser(@PathVariable Integer id){

        return ResponseEntity.status(HttpStatus.OK).body(passService.getPassByUser(id));
    }

    @GetMapping("/getByStatus/{status}")
    public ResponseEntity getPassByStatus(@PathVariable String status){

        return ResponseEntity.status(HttpStatus.OK).body(passService.getPassByStatus(status));
    }
    @GetMapping("/getBetweenPrice/{start_price}/{end_price}")
    public ResponseEntity getPassBetweenPrice(@PathVariable Double start_price ,@PathVariable Double end_price){

        return ResponseEntity.status(HttpStatus.OK).body(passService.getPassBetweenPrice(start_price, end_price));
    }
    @GetMapping("/getBetweenDate/{date}")
    public ResponseEntity getPassBetweenDate(@PathVariable@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        return ResponseEntity.status(HttpStatus.OK).body(passService.getPassBetweenDate(date));
    }

}

