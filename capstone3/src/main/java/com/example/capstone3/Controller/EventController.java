package com.example.capstone3.Controller;

import com.example.capstone3.DTO.EventDTO;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
public class EventController {
        private final EventService eventService;


    @GetMapping("/get")
    public ResponseEntity getEvents(){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEvents());
    }
    @PostMapping("/add")
    public ResponseEntity addEvent(@Valid @RequestBody EventDTO event){
        eventService.addEvent(event);
        return ResponseEntity.status(HttpStatus.OK).body("event added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateEvent(@PathVariable Integer id, @Valid @RequestBody EventDTO event){
        eventService.updateEvent(id, event);
        return ResponseEntity.status(HttpStatus.OK).body("event update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable Integer id){
        eventService.deleteEvent(id);
        return ResponseEntity.status(HttpStatus.OK).body("event delete");
    }
}
