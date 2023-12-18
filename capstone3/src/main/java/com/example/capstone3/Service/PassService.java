package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Pass;
import com.example.capstone3.Repository.PassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassService {
    private final PassRepository passRepository;


    public List<Pass> getPasses(){
        return passRepository.findAll();
    }

    public void addPass(Pass pass){
        passRepository.save(pass);
    }

    public void updatePass(Integer id , Pass pass){
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
