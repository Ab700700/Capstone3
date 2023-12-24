package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.OrdersDTO;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrderRepository orderRepository;
    private final BusinessRepository businessRepository;
    private final PlaceRepository placeRepository;
    private final EventRepository eventRepository;
    private final CompanyRepository companyRepository;

    //18
    public List<Orders> getAllOrdres(){
        return orderRepository.findAll();
    }

    public void addOrder(OrdersDTO orders){
        Business business = businessRepository.findBusinessById(orders.getBusinessid());
        if(business == null) throw new ApiException("Business account not found");
        Place  place = placeRepository.findPlaceById(orders.getPlaceid());
        if(place == null || place.getCompanyName() != null) throw new ApiException("Place not found");
        Event event = eventRepository.findEventById(orders.getEventid());
        if(event == null) throw  new ApiException("Event not found");
        if(business.getStatus().equals("notactive")) throw new ApiException("User cant make an order");
        if(orders.getCapacity()>place.getCapacity()) throw new ApiException("Place's capacity is not enough for the order");
        Orders newOrder = new Orders(null,business.getCompanyName(),orders.getCapacity(),orders.getDescription(),orders.getCategory(),"pending",LocalDateTime.now(),business,event,place);
        orderRepository.save(newOrder);

    }

    //19
    public String updateOrder(Integer id,OrdersDTO orders){
        Orders oldOrder =  orderRepository.findOrderById(id);
        if(oldOrder == null) throw new ApiException("Order not found");
        if(orders.getCapacity()> placeRepository.findPlaceById(orders.getPlaceid()).getCapacity()) throw new ApiException("Place's capacity is not enough");
        oldOrder.setDescription(orders.getDescription());
        oldOrder.setCapacity(orders.getCapacity());
        oldOrder.setCompanyName(orders.getCompanyName());
        orderRepository.save(oldOrder);
        return"Order updated";
    }
    //20
    public String deleteOrder(Integer id){
        Orders order = orderRepository.findOrderById(id);
        if(order == null) throw  new ApiException("Order not found");
        orderRepository.delete(order);
        return "Order canceled";
    }
    //21
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
            if(!o.getId().equals(orderid)){
                o.setStatus("denied");
                orderRepository.save(o);
            }
        }
        orderRepository.save(orders);
        placeRepository.save(place);
        return "Order accepted";
    }
    //22
    public List<Orders> getEventOrders(Integer eventid){
        return  orderRepository.getOrdersbyeventid(eventid);
    }
    //23
    public List<Orders> getOrdersByPlace(Integer placeid){
        return orderRepository.getOrdersByplaceid(placeid);
    }
    //24
    public List<Orders> getOrdersByStatus(String status){
        return orderRepository.findOrdersByStatus(status);
    }
    //25
    public List<Orders> ordersAfter(LocalDate date){
        LocalDateTime newDate = date.atStartOfDay();
        return orderRepository.findOrdersByOrderdateAfter(newDate);
    }
    //27
    public List<Orders> ordersBefore(LocalDate date){
        LocalDateTime newDate = date.atStartOfDay();
        return orderRepository.findOrdersByOrderdateBefore(newDate);
    }

    //28
    public List<Orders> ordersBetween(LocalDate date1, LocalDate date2){
        LocalDateTime newDate1,newDate2;
        newDate1 = date1.atStartOfDay();
        newDate2 = date2.atStartOfDay();
        return orderRepository.findOrdersByOrderdateBetween(newDate1,newDate2);
    }


}
