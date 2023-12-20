package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.OrdersDTO;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
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
    private final CompanyRepository companyRepository;
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
        if(business.getStatus().equals("notactive")) throw new ApiException("User cant make an order");
        if(orders.getCapacity()>place.getCapacity()) throw new ApiException("Place's capacity is not enough for the order");
        Orders newOrder = new Orders(null,orders.getCompanyName(),orders.getCapacity(),orders.getDescription(),orders.getCategory(),"pending",business,event,place);
        orderRepository.save(newOrder);

    }


    public String updateOrder(Integer id,OrdersDTO orders){
        Orders oldOrder =  orderRepository.findOrderById(id);
        if(oldOrder == null) throw new ApiException("Order not found");
        if(orders.getCapacity()> placeRepository.findPlaceById(orders.getPlaceid()).getCapacity()) throw new ApiException("Place's capacity is not enough");
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

    public String acceptOrder(Integer orderid, Integer comid){
        Orders orders = orderRepository.findOrderById(orderid);
        if(orders == null) throw new ApiException("Order not found");
        Company company = companyRepository.findCompaniesById(comid);
        if(company == null) throw  new ApiException("Company not found");
        if(orders.getStatus().equals("accepted")) throw  new ApiException("Order already accepted");
        Place place = orders.getPlaceorder();
        place.setCapacity(orders.getCapacity());
        place.setCompanyName(orders.getCompanyName());
        place.setDescription(orders.getDescription());
        orders.setStatus("accepted");
        for(Orders o : place.getOrders()){
            if(o.getId()!=orderid){
                o.setStatus("denied");
                orderRepository.save(o);
            }
        }
        orderRepository.save(orders);
        placeRepository.save(place);
        return "Order accepted";
    }

    public List<Orders> getEventOrders(Integer eventid){
        return  orderRepository.getOrdersbyeventid(eventid);
    }



}
