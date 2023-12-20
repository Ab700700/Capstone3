package com.example.capstone3.Repository;

import com.example.capstone3.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

    Orders findOrderById(Integer id);
    @Query("select o from Orders o where o.event.id =?1")
    List<Orders> getOrdersbyeventid(Integer eventid);
}
