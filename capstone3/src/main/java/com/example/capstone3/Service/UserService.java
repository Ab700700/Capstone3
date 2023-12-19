package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Pass;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.PassRepository;
import com.example.capstone3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PassRepository passRepository;


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
        pass.setUser(user);
        passRepository.save(pass);
    }

}
