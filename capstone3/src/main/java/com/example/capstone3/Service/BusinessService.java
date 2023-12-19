package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Business;
import com.example.capstone3.Repository.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {

    public final BusinessRepository businessRepository;

    public List<Business> getAllBusinesses(){
        return businessRepository.findAll();
    }

    public void addBusiness(Business business){
        businessRepository.save(business);
    }

    public String updateBusiness(Integer id, Business business){
        Business oldBusiness = businessRepository.findBusinessById(id);
        if(oldBusiness == null) throw new ApiException("Business not found");
        business.setId(id);
        businessRepository.save(business);
        return "Business updated";
    }

    public String deleteBusiness(Integer id){
        Business business = businessRepository.findBusinessById(id);
        if(business == null) throw new ApiException("Business not found");
        businessRepository.delete(business);
        return "Business deleted";
    }
}
