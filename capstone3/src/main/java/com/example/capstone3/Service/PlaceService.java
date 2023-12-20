package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.PlaceDTO;
import com.example.capstone3.Model.Business;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Model.Place;
import com.example.capstone3.Repository.BusinessRepository;
import com.example.capstone3.Repository.EventRepository;
import com.example.capstone3.Repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private  final PlaceRepository placeRepository;
    private  final BusinessRepository businessRepository;
    private final EventRepository eventRepository;
    public List<Place> getAllPlaces(){
       return placeRepository.findAll();
    }

    public void addPlace( PlaceDTO place){

        Event event = eventRepository.findEventById(place.getEvent_id());
        if(event == null) throw new ApiException("Event account not found");

       Place place1 =new Place(null,place.getCompanyName(),place.getCategory(),place.getDescription(),place.getCapacity(),null,null,event,null);
       placeRepository.save(place1);
    }

    public String updatePlace(Integer id,PlaceDTO p){
        Place place = placeRepository.findPlaceById(id);
        if(place == null) throw  new ApiException("Place not found");
        place.setCategory(p.getCategory());
        place.setCompanyName(p.getCompanyName());
        place.setDescription(p.getDescription());
        place.setCategory(p.getCategory());
        placeRepository.save(place);
        return "Place updated";
    }

    public String deletePlace(Integer id){
        Place place = placeRepository.findPlaceById(id);
        if(place == null) throw  new ApiException("Place not found");
        placeRepository.delete(place);
        return "Place deleted";
    }

    public List<Place> searchByCompanyName(String name){
        return placeRepository.findPlacesByCompanyName(name);
    }

    public List<Place> searchByCategory(String category){
        return placeRepository.findPlacesByCategoryStartsWith(category);
    }

}
