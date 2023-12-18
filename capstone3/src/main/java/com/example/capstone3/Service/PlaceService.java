package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
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

    public void addPlace(Integer bid,Integer event_id,Place place){
        Business business = businessRepository.findBusinessById(bid);
        if(business == null || !place.getCompanyName().equals(business.getCompanyName())) throw new ApiException("Business account not found");
        Event event = eventRepository.findEventById(event_id);
        if(event == null) throw new ApiException("Event account not found");
        business.getPlaces().add(place);
        // I think event is not finished yet, I cant get places from event.
    }

    public String updatePlace(Integer id,Place p){
        Place place = placeRepository.findPlaceById(id);
        if(place == null) throw  new ApiException("Place not found");
        p.setId(id);
        placeRepository.save(p);
        return "Place updated";
    }

    public String deletePlace(Integer id){
        Place place = placeRepository.findPlaceById(id);
        if(place == null) throw  new ApiException("Place not found");
        placeRepository.delete(place);
        return "Place deleted";
    }


}
