package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PassRepository passRepository;
    private final CompanyRepository companyRepository;
    private final BusinessRepository businessRepository;
    private final EventRepository eventRepository;


    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        user.setRole("visitor");
        userRepository.save(user);
    }

    public void updateUser(Integer id , User user,String password){
        User oldUser = userRepository.findUserById(id);
        if(oldUser==null||!oldUser.getPassword().equals(password)){
            throw new ApiException("user id not found or wrong password");
        }

        oldUser.setFirstname(user.getFirstname());
        oldUser.setLastname(user.getLastname());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhone_number(user.getPhone_number());
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id);
        if(user==null){
            throw new ApiException("user id not found");
        }
        userRepository.delete(user);
    }

    public void assignUserToPass(Integer user_id,Integer pass_id){
        Pass pass = passRepository.findPassById(pass_id);
        User user =userRepository.findUserById(user_id);
        if(user ==null){
            throw new ApiException("user id not found");
        }
        if(pass==null){
            throw new ApiException("pass id not found");
        }
        if(pass.getStatus().equals("active")){
            throw new ApiException("the pass is taken");
        }
        Event event = pass.getEvent();
        Company company =event.getCompany();
        event.setTickets(event.getTickets()-1);
        company.setProfit(company.getProfit()+pass.getPrice());
        pass.setUser(user);
        pass.setStatus("active");
        passRepository.save(pass);
        eventRepository.save(event);
        companyRepository.save(company);
    }


    public void ActivateCompany(Integer user_id , Integer company_id){
        Company company = companyRepository.findCompaniesByIdAndStatus(company_id,"notactive");
        User user =userRepository.findUserById(user_id);
        if(user ==null){
            throw new ApiException("user id not found");
        }
        if(!user.getRole().equals("admin")){
            throw new ApiException("user not admin");
        }
        if(company==null){
            throw new ApiException("company id not found");
        }

        company.setStatus("active");
        companyRepository.save(company);
    }
    public void ActivateBusiness(Integer user_id , Integer business_id){
        Business business =businessRepository.findBusinessByIdAndStatus(business_id,"notactive");
        User user =userRepository.findUserById(user_id);
        if(user ==null){
            throw new ApiException("user id not found");
        }
        if(!user.getRole().equals("admin")){
            throw new ApiException("user not admin");
        }
        if(business==null){
            throw new ApiException("business id not found");
        }

      business.setStatus("active");
        businessRepository.save(business);
    }


    public Set<Contest> getContestByUser(Integer id){
        User user = userRepository.findUserById(id);
        if(user==null){
            throw new ApiException("user id not found");
        }

        return user.getContests();
    }

}
