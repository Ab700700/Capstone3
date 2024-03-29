package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.PassDTO;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Model.Pass;
import com.example.capstone3.Repository.EventRepository;
import com.example.capstone3.Repository.PassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PassService {
    private final PassRepository passRepository;
    private final EventRepository eventRepository;

    // 43
    public List<Pass> getPasses(){

        return passRepository.findAll();
    }
    // 44
    public void addPass(PassDTO pass){
        Event event = eventRepository.findEventById(pass.getEvent_id());
        if(event==null){
            throw new ApiException("event id not found");
        }
        if(pass.getStart_date().compareTo(pass.getEnd_date())>0){
            throw new ApiException("not valid date");
        }
        if(pass.getStart_date().compareTo(event.getStart_date())<0 || pass.getEnd_date().compareTo(event.getEnd_date())>0){
            throw new ApiException("not valid pass date");
        }
        Pass pass1 = new Pass(null, "notactive", pass.getStart_date(),pass.getEnd_date(),pass.getPrice(),null,event);
        passRepository.save(pass1);
    }
    // 45
    public void updatePass(Integer id , PassDTO pass){
        Pass oldPass = passRepository.findPassById(id);

        if(oldPass==null){
            throw new ApiException("pass id not found");
        }
        oldPass.setStart_date(pass.getStart_date());
        oldPass.setEnd_date(pass.getEnd_date());
        oldPass.setPrice(pass.getPrice());
        passRepository.save(oldPass);
    }
    // 46
    public void deletePass(Integer id){
        Pass pass = passRepository.findPassById(id);
        if(pass==null){
            throw new ApiException("pass id not found");
        }
        passRepository.delete(pass);
    }
    // 47
    public List<Pass> getPassByUser(Integer id){
        List<Pass> passes = passRepository.findPassesById(id);
        if(passes.isEmpty()){
            throw new ApiException("no passes for this id");
        }
        return passes;
    }
    // 48
    public List<Pass> getPassByStatus(String string){
        List<Pass> passes = passRepository.findPassByStatus(string);
        if(passes.isEmpty()){
            throw new ApiException("no passes with this status");
        }
        return passes;
    }
    // 49
    public List<Pass> getPassBetweenPrice(Double start_price,Double end_price){
        List<Pass> passes = passRepository.findPassByPriceBetween(start_price, end_price);
        if(passes.isEmpty()){
            throw new ApiException("no passes between these prices");
        }
        return passes;
    }
    // 50
    public List<Pass> getPassBetweenDate(LocalDate date){
        LocalDateTime newDate = date.atStartOfDay();
        List<Pass> passes = passRepository.PassByStart_dateAfterAndEnd_dateBefore(newDate);
        if(passes.isEmpty()){
            throw new ApiException("no passes between these date");
        }
        return passes;
    }


}
