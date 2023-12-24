package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.EventDTO;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final CompanyRepository companyRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final PassRepository passRepository;


    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    public void addEvent(EventDTO event){
        Company company = companyRepository.findCompaniesById(event.getCompany_id());
        if(company==null){
            throw new ApiException("company id not found");
        }
        if(!company.getStatus().equals("active")){
            throw new ApiException("company not active");
        }
        if(event.getStart_date().compareTo(event.getEnd_date())>0){
            throw new ApiException("not valid date");
        }
        if(!company.getUsers().isEmpty()){
            for (User u: company.getUsers()){
                Message message = new Message(null,company.getCompany_name()+" new event","new",u);
                messageRepository.save(message);
                userRepository.save(u);


            }
        }
        Event event1=new Event(null, event.getEvent_name(), event.getStart_date(),event.getEnd_date(),event.getTickets(),event.getCategory(),event.getCity(),null,company,null,null);
        eventRepository.save(event1);
    }

    public void updateEvent(Integer id , EventDTO event){
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
        Company company = companyRepository.findCompaniesById(event.getCompany().getId());
        if(company==null){
            throw new ApiException("company id not found");
        }



        company.setEvents(null);
        companyRepository.save(company);
        eventRepository.delete(event);

    }


    public List<Event> getEventBetweenDate(LocalDate date){
        LocalDateTime newDate=date.atStartOfDay();
        List<Event> events = eventRepository.EventByStart_dateAfterAndEnd_dateBefore(newDate);
        if(events.isEmpty()){
            throw new ApiException("no events between this date");
        }
        return events;
    }

    public List<Event> getEventAvailable(){
        List<Event> events = eventRepository.findEventByTicketsGreaterThan(0);
        if(events.isEmpty()){
            throw new ApiException("no events available");
        }
        return events;
    }
    public List<Event> getEventByCategory(String category){
        List<Event> events = eventRepository.findEventByCategory(category);
        if(events.isEmpty()){
            throw new ApiException("no events with this category");
        }
        return events;
    }
    public List<Event> getEventByCity(String city){
        List<Event> events = eventRepository.findEventByCity(city);
        if(events.isEmpty()){
            throw new ApiException("no events with this city");
        }
        return events;
    }

    public Set<User> getVisitorsForEvent(Integer id){
        Event event =eventRepository.findEventById(id);
        Set<User> users = new HashSet<>();
        if(event==null){
            throw new ApiException("event id not found");
        }
      for(Pass p : event.getPasses()){
          if(p.getUser()!=null){
              users.add(p.getUser());
          }

      }
      return users;
    }

}
