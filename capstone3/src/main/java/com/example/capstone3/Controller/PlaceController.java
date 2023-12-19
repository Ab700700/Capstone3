package com.example.capstone3.Controller;

import com.example.capstone3.DTO.PlaceDTO;
import com.example.capstone3.Model.Place;
import com.example.capstone3.Service.PlaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event/place")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping("/get")
    public ResponseEntity getAllPlaces(){
        return ResponseEntity.status(HttpStatus.OK).body(placeService.getAllPlaces());
    }
    @PostMapping("/add")
    public ResponseEntity addPlace(@RequestBody @Valid PlaceDTO place){
        placeService.addPlace(place);
        return ResponseEntity.status(HttpStatus.OK).body("Place added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePlace(@PathVariable Integer id , @RequestBody @Valid PlaceDTO place){
        return ResponseEntity.status(HttpStatus.OK).body(placeService.updatePlace(id,place));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePlace(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(placeService.deletePlace(id));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity searchByCompanyName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(placeService.searchByCompanyName(name));
    }
    @GetMapping("/search-category/{word}")
    public ResponseEntity searchByCategory(@PathVariable String word){
        return ResponseEntity.status(HttpStatus.OK).body(placeService.searchByCategory(word));
    }

}
