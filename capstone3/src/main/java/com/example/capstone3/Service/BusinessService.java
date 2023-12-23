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
        business.setStatus("notactive");
        businessRepository.save(business);
    }

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

    public String deleteBusiness(Integer id){
        Business business = businessRepository.findBusinessById(id);
        if(business == null) throw new ApiException("Business not found");
        businessRepository.delete(business);
        return "Business deleted";
    }

    public List<Business> businessesByStatus(String status){
        return businessRepository.findBusinessesByStatus(status);
    }

    public Business businessByName(String name){
        return businessRepository.findBusinessByCompanyName(name);
    }
}
