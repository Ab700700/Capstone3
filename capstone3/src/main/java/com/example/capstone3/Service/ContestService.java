package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Business;
import com.example.capstone3.Model.Contest;
import com.example.capstone3.Model.Place;
import com.example.capstone3.Repository.BusinessRepository;
import com.example.capstone3.Repository.ContestRepository;
import com.example.capstone3.Repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;
    private final PlaceRepository placeRepository;
    private final BusinessRepository businessRepository;
    public List<Contest> getAllContests(){
        return contestRepository.findContestsByStatus("public");
    }

    public void addContest(Integer pid,Contest contest){
        Place place = placeRepository.findPlaceById(pid);
        if(place == null) throw new ApiException("Place not found");
        place.getContests().add(contest);
        placeRepository.save(place);
        contestRepository.save(contest);
    }

    public String updateContest(Integer id, Contest contest){
        Contest oldContest = contestRepository.findContestById(id);
        if(oldContest == null || !oldContest.getPlace().equals(contest.getPlace())) throw new ApiException("Contest is not there");
        Business business = businessRepository.findBusinessByCompanyName(contest.getPlace().getCompanyName());
        if(business == null) throw  new ApiException("Business account not found");
        contest.setId(id);
        contestRepository.save(contest);
        return "Contest updated";
    }

    public String deleteContest(Integer id){
        Contest contest = contestRepository.findContestById(id);
        if(contest == null) throw  new ApiException("Contest not found");
        contestRepository.delete(contest);
        return "Contest removed";
    }
}
