package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.ContestDTO;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.BusinessRepository;
import com.example.capstone3.Repository.ContestRepository;
import com.example.capstone3.Repository.PlaceRepository;
import com.example.capstone3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
        if(place.getCompanyName()==null) throw new ApiException("No company there");
        if(contest.getEnddate().isBefore(contest.getStartdate()) || place.getEvent().getEnd_date().isBefore(contest.getStartdate()) || contest.getEnddate().isAfter(place.getEvent().getEnd_date())|| contest.getStartdate().isBefore(place.getEvent().getStart_date())) throw new ApiException("Set dates in right way");
        Contest contest1 = new Contest(null,0,contest.getDescription(),contest.getStatus(),contest.getStartdate(),contest.getEnddate(),place,null);
        place.getContests().add(contest1);
        placeRepository.save(place);
        contestRepository.save(contest1);
    }

    public String updateContest(Integer id, ContestDTO contest){
        Contest oldContest = contestRepository.findContestById(id);
        if(oldContest == null) throw new ApiException("Contest is not there");
        Business business = businessRepository.findBusinessByCompanyName(placeRepository.findPlaceById(contest.getPlaceid()).getCompanyName());
        if(business == null) throw  new ApiException("Business account not found");
        Place place = placeRepository.findPlaceById(contest.getPlaceid());
        if(contest.getEnddate().isBefore(contest.getStartdate()) || place.getEvent().getEnd_date().isBefore(contest.getStartdate()) || contest.getEnddate().isAfter(place.getEvent().getEnd_date())|| contest.getStartdate().isBefore(place.getEvent().getStart_date())) throw new ApiException("Set dates in right way");
        oldContest.setCompetitors(contest.getCompetitors());
        oldContest.setStatus(contest.getStatus());
        oldContest.setStartdate(contest.getStartdate());
        oldContest.setEnddate(contest.getEnddate());
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
        if(user == null|| user.getRole().equals("admin")) throw  new ApiException("User not found or not allowed");
        for(Contest c : user.getContests()){
            if(c.getId().equals(contestid)) throw  new ApiException("User already there");
        }
        Contest contest = contestRepository.findContestById(contestid);
        if(contest == null) throw new ApiException("Contest not found");
        contest.setCompetitors(contest.getCompetitors()+1);
        user.getContests().add(contest);
        contest.getUsers().add(user);
        contestRepository.save(contest);
        userRepository.save(user);
        return user.getFirstname()+" "+user.getLastname()+" assigned to contest id: "+contest.getId();
    }

    public List<Contest> searchByStatus(String status){
        return contestRepository.findContestsByStatus(status);
    }

    public List<Contest> searchBelow(Integer number){
        if(number<0) throw new ApiException("Wrong number");
        return contestRepository.findContestsByCompetitorsBefore(number);
    }
    public List<Contest> searchAbove(Integer number){
        if(number<0) throw new ApiException("Wrong number");
        return contestRepository.findContestByCompetitorsAfter(number);
    }

    public String removecompetitor(Integer contestid, Integer competitorid){
        User user = userRepository.findUserById(competitorid);
        if(user == null) throw  new ApiException("Competitor not found");
        Contest contest = contestRepository.findContestById(contestid);
        if(contest ==null) throw new ApiException("Contest not found");
        for(Contest c : user.getContests()){
            if(c.getId().equals(contestid)) {
                c.getUsers().remove(user);
                contestRepository.save(c);
            }
        }
        for(User u : contest.getUsers()){
            if(u.getId().equals(competitorid)){
                u.getContests().remove(contest);
                userRepository.save(u);
            }
        }
        if(contest.getCompetitors()>0){
            contest.setCompetitors(contest.getCompetitors()-1);
            contestRepository.save(contest);
        }

        return "Competitor removed from contest";
    }

    public Set<User> getCompetitorsForContest(Integer contestid){
        return contestRepository.findContestById(contestid).getUsers();
    }

    public List<Contest> getContestStartBetween(LocalDate date1, LocalDate date2){
        LocalDateTime ldate1,ldate2;
        ldate1 = date1.atStartOfDay();
        ldate2 = date2.atStartOfDay();
        return contestRepository.findContestByStartdateBetween(ldate1,ldate2);
    }

    public List<Contest> getContestEndBetween(LocalDate date1, LocalDate date2){
        LocalDateTime ldate1,ldate2;
        ldate1 = date1.atStartOfDay();
        ldate2 = date2.atStartOfDay();
        return contestRepository.findContestByEnddateBetween(ldate1,ldate2);
    }

}
