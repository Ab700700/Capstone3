package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.ContestDTO;
import com.example.capstone3.Model.Business;
import com.example.capstone3.Model.Contest;
import com.example.capstone3.Model.Place;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.BusinessRepository;
import com.example.capstone3.Repository.ContestRepository;
import com.example.capstone3.Repository.PlaceRepository;
import com.example.capstone3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;
    private final PlaceRepository placeRepository;
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;
    public List<Contest> getAllContests(){
        return contestRepository.findAll();
    }

    public void addContest(ContestDTO contest){
        Place place = placeRepository.findPlaceById(contest.getPlaceid());
        if(place == null) throw new ApiException("Place not found");
        Contest contest1 = new Contest(null,0,contest.getDescription(),contest.getStatus(),place,null);
        place.getContests().add(contest1);
        placeRepository.save(place);
        contestRepository.save(contest1);
    }

    public String updateContest(Integer id, ContestDTO contest){
        Contest oldContest = contestRepository.findContestById(id);
        if(oldContest == null) throw new ApiException("Contest is not there");
        Business business = businessRepository.findBusinessByCompanyName(placeRepository.findPlaceById(contest.getPlaceid()).getCompanyName());
        if(business == null) throw  new ApiException("Business account not found");
        oldContest.setCompetitors(contest.getCompetitors());
        oldContest.setStatus(contest.getStatus());
        oldContest.setDescription(contest.getDescription());
        contestRepository.save(oldContest);
        return "Contest updated";
    }

    public String deleteContest(Integer id){
        Contest contest = contestRepository.findContestById(id);
        if(contest == null) throw  new ApiException("Contest not found");
        contestRepository.delete(contest);
        return "Contest removed";
    }

    public String userAssignToContest(Integer uid, Integer contestid){
        User user = userRepository.findUserById(uid);
        for(Contest c : user.getContests()){
            if(c.getId().equals(contestid)) throw  new ApiException("User already there");
        }
        if(user == null|| user.getRole().equals("admin")) throw  new ApiException("User not found or not allowed");
        Contest contest = contestRepository.findContestById(contestid);
        if(contest == null) throw new ApiException("Contest not found");
        contest.setCompetitors(user.getContests().size());
        user.getContests().add(contest);
        contest.getUsers().add(user);
        contestRepository.save(contest);
        userRepository.save(user);
        return user.getFirstname()+" "+user.getLastname()+" assigned to contest id: "+contest.getId();
    }
}
