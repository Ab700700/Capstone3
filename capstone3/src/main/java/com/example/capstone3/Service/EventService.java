package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;


    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    public void addEvent(Event event){
        eventRepository.save(event);
    }

    public void updateEvent(Integer id , Event event){
        Event oldEvent = eventRepository.findEventById(id);
        if(oldEvent==null){
            throw new ApiException("event id not found");
        }
        oldEvent.setEvent_name(event.getEvent_name());
        oldEvent.setStart_date(event.getStart_date());
        oldEvent.setEnd_date(event.getEnd_date());
        oldEvent.setTickets(event.getTickets());
        eventRepository.save(oldEvent);
    }

    public void deleteEvent(Integer id){
        Event event =eventRepository.findEventById(id);
        if(event==null){
            throw new ApiException("event id not found");
        }
        eventRepository.delete(event);
    }
}
