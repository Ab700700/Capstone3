package com.example.capstone3.Repository;

import com.example.capstone3.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {


    Event findEventById(Integer id);
    @Query("select e from Event e where e.start_date<?1 and e.end_date>?1")
    List<Event> EventByStart_dateAfterAndEnd_dateBefore(LocalDateTime date);

    List<Event> findEventByTicketsGreaterThan(Integer tickets);
    List<Event> findEventByCategory(String category);
    List<Event> findEventByCity(String city);
}
