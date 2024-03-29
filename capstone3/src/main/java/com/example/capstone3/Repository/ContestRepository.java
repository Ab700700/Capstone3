package com.example.capstone3.Repository;

import com.example.capstone3.Model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest,Integer> {

    Contest findContestById(Integer id);

    List<Contest> findContestsByStatus(String status);

    List<Contest> findContestsByCompetitorsBefore(Integer number);
    List<Contest> findContestByCompetitorsAfter(Integer number);

    List<Contest> findContestByStartdateBetween(LocalDateTime date1 , LocalDateTime date2);
    List<Contest> findContestByEnddateBetween(LocalDateTime date1, LocalDateTime date2);
}
