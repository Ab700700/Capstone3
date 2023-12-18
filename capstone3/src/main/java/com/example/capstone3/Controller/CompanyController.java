package com.example.capstone3.Controller;

import com.example.capstone3.Model.Company;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/get")
    public ResponseEntity getCompany(){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompany());
    }
    @PostMapping("/add")
    public ResponseEntity addCompany(@Valid @RequestBody Company company){
        companyService.addCompany(company);
        return ResponseEntity.status(HttpStatus.OK).body("company added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCompany(@PathVariable Integer id, @Valid @RequestBody Company company){
        companyService.updateCompany(id, company);
        return ResponseEntity.status(HttpStatus.OK).body("company update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCompany(@PathVariable Integer id){
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.OK).body("company delete");
    }
}
