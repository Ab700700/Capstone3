package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.OrdersDTO;
import com.example.capstone3.Model.Business;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Model.Orders;
import com.example.capstone3.Model.Place;
import com.example.capstone3.Repository.BusinessRepository;
import com.example.capstone3.Repository.EventRepository;
import com.example.capstone3.Repository.OrderRepository;
import com.example.capstone3.Repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrderRepository orderRepository;
    private final BusinessRepository businessRepository;
    private final PlaceRepository placeRepository;
    private final EventRepository eventRepository;
    public List<Orders> getAllOrdres(){
        return orderRepository.findAll();
    }

    public void addOrder(OrdersDTO orders){
        Business business = businessRepository.findBusinessById(orders.getBusinessid());
        if(business == null) throw new ApiException("Business account not found");
        Place  place = placeRepository.findPlaceById(orders.getPlaceid());
        if(place == null) throw new ApiException("Place not found");
        Event event = eventRepository.findEventById(orders.getEventid());
        if(event == null) throw  new ApiException("Event not found");
        Orders newOrder = new Orders(null,orders.getCompanyName(),orders.getCapacity(),orders.getDescription(),"pending",business,event,place);
        orderRepository.save(newOrder);

    }


    public String updateOrder(Integer id,OrdersDTO orders){
        Orders oldOrder =  orderRepository.findOrderById(id);
        if(oldOrder == null) throw new ApiException("Order not found");
        oldOrder.setDescription(orders.getDescription());
        oldOrder.setCapacity(orders.getCapacity());
       // oldOrder.setStatus(orders.getStatus());
        oldOrder.setCompanyName(orders.getCompanyName());
        orderRepository.save(oldOrder);
        return"Order updated";
    }

    public String deleteOrder(Integer id){
        Orders order = orderRepository.findOrderById(id);
        if(order == null) throw  new ApiException("Order not found");
        orderRepository.delete(order);
        return "Order canceled";
    }





}
