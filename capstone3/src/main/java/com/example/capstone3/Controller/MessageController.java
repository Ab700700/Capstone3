package com.example.capstone3.Controller;

import com.example.capstone3.Model.Message;
import com.example.capstone3.Service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    @GetMapping("/get")
    public ResponseEntity getMessage(){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessages());
    }
    @PostMapping("/add")
    public ResponseEntity addMessage(@RequestBody Message message){
        messageService.addMessage(message);
        return ResponseEntity.status(HttpStatus.OK).body("message added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMessage(@PathVariable Integer id, @RequestBody Message message){
        messageService.updateMessage(id,message);
        return ResponseEntity.status(HttpStatus.OK).body("message update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity addMessage(@PathVariable Integer id){
        messageService.deleteMessage(id);
        return ResponseEntity.status(HttpStatus.OK).body("message delete");
    }
    @GetMapping("/getByStatus/{id}/{status}")
    public ResponseEntity getMessageByUserAndStatus(@PathVariable Integer id,@PathVariable String status){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getByUserAndStatus(id, status));
    }
    @GetMapping("/getMessageById/{id}")
    public ResponseEntity getMessageById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessageById(id));
    }




}
