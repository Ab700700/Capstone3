package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Business;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Model.Orders;
import com.example.capstone3.Repository.BusinessRepository;
import com.example.capstone3.Repository.EventRepository;
import com.example.capstone3.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrderRepository orderRepository;
    private final BusinessRepository businessRepository;
    private final EventRepository eventRepository;
    public List<Orders> getAllOrdres(){
        return orderRepository.findAll();
    }

    public void addOrder(Integer bid,Integer event_id,Orders orders){
        Business business = businessRepository.findBusinessById(bid);
        if(business == null) throw new ApiException("Business account not found");
        Event event = eventRepository.findEventById(event_id);
        if(event == null) throw new ApiException("Event account not found");
        business.getOrders().add(orders);
        event.getOrders().add(orders);
        orderRepository.save(orders);
        businessRepository.save(business);
        eventRepository.save(event);
    }

//
//    public String updateOrder(Integer bid ,Integer event_id, Orders orders){
//        Orders oldOrder = orderRepository.findOrdersByBusinessAndEvent(bid,event_id);
//        if(oldOrder == null) throw new ApiException("Order not found");
//        orders.setId(oldOrder.getId());
//        orderRepository.save(orders);
//        return"Order updated";
//    }
//
//    public String deleteOrder(Integer bid, Integer even_id){
//        Orders order = orderRepository.findOrdersByBusinessAndEvent(bid,even_id);
//        if(order == null) throw  new ApiException("Order not found");
//        orderRepository.delete(order);
//        return "Order canceled";
//    }





}
