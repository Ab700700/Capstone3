package com.example.capstone3.Controller;

import com.example.capstone3.Model.Business;
import com.example.capstone3.Service.BusinessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event/business")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;
    @GetMapping("/get")
    public ResponseEntity getAllBusinesses(){
        return ResponseEntity.status(HttpStatus.OK).body(businessService.getAllBusinesses());
    }
    @PostMapping("/add")
    public ResponseEntity addBusniess(@RequestBody @Valid Business business){
        businessService.addBusiness(business);
        return ResponseEntity.status(HttpStatus.OK).body("Business added");
    }
    @PutMapping("/update/{id}/{password}")
    public ResponseEntity updateBusiness(@PathVariable Integer id, @PathVariable String password,@RequestBody @Valid Business business){
        return ResponseEntity.status(HttpStatus.OK).body(businessService.updateBusiness(id,password,business));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBusiness(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(businessService.deleteBusiness(id));
    }
    @GetMapping("/search-by-status/{status}")
    public ResponseEntity businessesByStatus(@PathVariable String status){
        return ResponseEntity.status(HttpStatus.OK).body(businessService.businessesByStatus(status));
    }
    @GetMapping("/search/{name}")
    public ResponseEntity busniessByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(businessService.businessByName(name));
    }
}
