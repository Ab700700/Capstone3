package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.EventDTO;
import com.example.capstone3.Model.Company;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Repository.CompanyRepository;
import com.example.capstone3.Repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final CompanyRepository companyRepository;


    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    public void addEvent(EventDTO event){
        Company company = companyRepository.findCompaniesById(event.getCompany_id());
        if(company==null){
            throw new ApiException("company id not found");
        }
        Event event1=new Event(null, event.getEvent_name(),event.getStart_date(),event.getEnd_date(),event.getTickets(),null,company,null,null);
//        event1.setEvent_name(event.getEvent_name());
//        event1.setStart_date(event.getStart_date());
//        event1.setEnd_date(event.getEnd_date());
//        event1.setTickets(event.getTickets());
//        event1.setCompany(company);
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
        Company company = companyRepository.findCompaniesById(event.getCompany().getId());
        if(event==null || company==null){
            throw new ApiException("event or company id not found");
        }
        company.setEvents(null);
        eventRepository.delete(event);
        companyRepository.save(company);
    }
}
