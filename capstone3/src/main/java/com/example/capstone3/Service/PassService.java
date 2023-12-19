package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.PassDTO;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Model.Pass;
import com.example.capstone3.Repository.EventRepository;
import com.example.capstone3.Repository.PassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassService {
    private final PassRepository passRepository;
    private final EventRepository eventRepository;


    public List<Pass> getPasses(){

        return passRepository.findAll();
    }

    public void addPass(PassDTO pass){
        Event event = eventRepository.findEventById(pass.getEvent_id());
        if(event==null){
            throw new ApiException("event id not found");
        }
        Pass pass1 = new Pass(null, pass.getStatus(), pass.getStart_date(),pass.getEnd_date(),pass.getPrice(),null,event);
        passRepository.save(pass1);
    }

    public void updatePass(Integer id , PassDTO pass){
        Pass oldPass = passRepository.findPassById(id);

        if(oldPass==null){
            throw new ApiException("pass id not found");
        }
        oldPass.setStart_date(pass.getStart_date());
        oldPass.setEnd_date(pass.getEnd_date());
        oldPass.setStatus(pass.getStatus());
        oldPass.setPrice(pass.getPrice());
        passRepository.save(oldPass);
    }

    public void deletePass(Integer id){
        Pass pass = passRepository.findPassById(id);
        if(pass==null){
            throw new ApiException("pass id not found");
        }
        passRepository.delete(pass);
    }
}
