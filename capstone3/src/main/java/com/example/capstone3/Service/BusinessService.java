package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Business;
import com.example.capstone3.Model.Place;
import com.example.capstone3.Repository.BusinessRepository;
import com.example.capstone3.Repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {

    public final BusinessRepository businessRepository;
    public final PlaceRepository placeRepository;

    //1
    public List<Business> getAllBusinesses(){
        return businessRepository.findAll();
    }
    //2
    public void addBusiness(Business business){
        business.setStatus("notactive");
        businessRepository.save(business);
    }
    //3
    public String updateBusiness(Integer id,String password, Business business){
        Business oldBusiness = businessRepository.findBusinessById(id);
        if(oldBusiness == null|| !oldBusiness.getPassword().equals(password)) throw new ApiException("Business not found or password is wrong");
        oldBusiness.setPhoneNumber(business.getPhoneNumber());
        oldBusiness.setDescription(business.getDescription());
        oldBusiness.setPassword(business.getPassword());
        oldBusiness.setCompanyName(business.getCompanyName());
        oldBusiness.setCommercialRegisterNum(business.getCommercialRegisterNum());
        businessRepository.save(oldBusiness);
        return "Business updated";
    }
    //4
    public String deleteBusiness(Integer id){
        Business business = businessRepository.findBusinessById(id);
        if(business == null) throw new ApiException("Business not found");
        List<Place> businessPlaces = placeRepository.findPlacesByCompanyName(business.getCompanyName());
        for(Place place: businessPlaces){
            place.setCompanyName(null);
            placeRepository.save(place);
        }
        businessRepository.delete(business);
        return "Business deleted";
    }
    //5
    public List<Business> businessesByStatus(String status){
        return businessRepository.findBusinessesByStatus(status);
    }
    //6
    public Business businessByName(String name){
        return businessRepository.findBusinessByCompanyName(name);
    }
}
