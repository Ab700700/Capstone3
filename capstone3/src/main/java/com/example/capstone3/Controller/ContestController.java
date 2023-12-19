package com.example.capstone3.Controller;

import com.example.capstone3.Model.Contest;
import com.example.capstone3.Service.ContestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event/contest")
@RequiredArgsConstructor
public class ContestController {

        private final ContestService contestService;

        @GetMapping("/get")
        public ResponseEntity getAllContests(){
            return ResponseEntity.status(HttpStatus.OK).body(contestService.getAllContests());
        }
        @PostMapping("/add")
        public ResponseEntity addContest(@PathVariable Integer pid, @RequestBody @Valid Contest contest){
            contestService.addContest(pid,contest);
            return ResponseEntity.status(HttpStatus.OK).body("Contest added");
        }

        @PutMapping("/update/{id}")
        public ResponseEntity updateContest(@PathVariable Integer id, @RequestBody @Valid Contest contest){
            return ResponseEntity.status(HttpStatus.OK).body(contestService.updateContest(id,contest));
        }
        @DeleteMapping("/delete/{id}")
        public ResponseEntity deleteContest(@PathVariable Integer id){
            return  ResponseEntity.status(HttpStatus.OK).body(contestService.deleteContest(id));
        }

}
