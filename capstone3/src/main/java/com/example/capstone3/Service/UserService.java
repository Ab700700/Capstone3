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

    // 36
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        user.setRole("visitor");
        userRepository.save(user);
    }
    // 37
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
    //38
    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id);
        if(user==null){
            throw new ApiException("user id not found");
        }
        userRepository.delete(user);
    }
    // 39
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

    // 40
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
    //41
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

    //42
    public Set<Contest> getContestByUser(Integer id){
        User user = userRepository.findUserById(id);
        if(user==null){
            throw new ApiException("user id not found");
        }

        return user.getContests();
    }


    /*-----------------*/

    //34

    public String followCompany(Integer userid , Integer companyid){
        User user = userRepository.findUserById(userid);
        if(user == null) throw new ApiException("User not found");
        Company company = companyRepository.findCompaniesById(companyid);
        if(company == null) throw  new ApiException("Company not found");
        for(Company c : user.getCompanies()){
            if(c.equals(company)) throw new ApiException("User already follow the company");
        }
        user.getCompanies().add(company);
        company.getUsers().add(user);
        userRepository.save(user);
        companyRepository.save(company);
        return user.getFirstname()+" "+user.getLastname()+" follows "+company.getCompany_name();
    }

    //35
    public String unfollowCompany(Integer userid , Integer companyid){
        User user= userRepository.findUserById(userid);
        if(user == null) throw new ApiException("User not found");
        Company company = companyRepository.findCompaniesById(companyid);
        if(company == null) throw new ApiException("Company not found");
        boolean found = false;
        for(Company c :user.getCompanies()){
            if(c.equals(company)) found = true;
        }
        if(found){
            for(Company c : user.getCompanies()){
                if(c.equals(company)){
                    c.getUsers().remove(user);
                    companyRepository.save(c);
                }
            }

            for(User u: company.getUsers()){
                if(u.equals(user)){
                    u.getCompanies().remove(company);
                    userRepository.save(user);
                }
            }
            return user.getFirstname()+" "+user.getLastname()+" unfollow "+company.getCompany_name();
        }else{
            return user.getFirstname()+" "+user.getLastname()+" does not follow "+company.getCompany_name();
        }

    }
}
