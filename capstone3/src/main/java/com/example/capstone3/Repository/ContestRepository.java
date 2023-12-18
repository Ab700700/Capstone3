package com.example.capstone3.Repository;

import com.example.capstone3.Model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends JpaRepository<Contest,Integer> {

    Contest findContestById(Integer id);
}
