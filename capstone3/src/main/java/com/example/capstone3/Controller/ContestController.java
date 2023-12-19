package com.example.capstone3.Controller;

import com.example.capstone3.DTO.ContestDTO;
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
        public ResponseEntity addContest( @RequestBody @Valid ContestDTO contest){
            contestService.addContest(contest);
            return ResponseEntity.status(HttpStatus.OK).body("Contest added");
        }

        @PutMapping("/update/{id}")
        public ResponseEntity updateContest(@PathVariable Integer id, @RequestBody @Valid ContestDTO contest){
            return ResponseEntity.status(HttpStatus.OK).body(contestService.updateContest(id,contest));
        }
        @DeleteMapping("/delete/{id}")
        public ResponseEntity deleteContest(@PathVariable Integer id){
            return  ResponseEntity.status(HttpStatus.OK).body(contestService.deleteContest(id));
        }
        @PutMapping("/{uid}/assign/{contestid}")
        public ResponseEntity assignToContest(@PathVariable Integer uid,@PathVariable Integer contestid){
            return ResponseEntity.status(HttpStatus.OK).body(contestService.userAssignToContest(uid,contestid));
        }
        @GetMapping("/search/{status}")
        public ResponseEntity searchByStatus(@PathVariable String status){
            return ResponseEntity.status(HttpStatus.OK).body(contestService.searchByStatus(status));
        }
        @GetMapping("/search-low/{number}")
        public ResponseEntity searchBelow(@PathVariable Integer number){
            return ResponseEntity.status(HttpStatus.OK).body(contestService.searchBelow(number));
        }
        @GetMapping("/search-greater/{number}")
        public ResponseEntity searchAbove(@PathVariable Integer number){
            return ResponseEntity.status(HttpStatus.OK).body(contestService.searchAbove(number));
        }

}
