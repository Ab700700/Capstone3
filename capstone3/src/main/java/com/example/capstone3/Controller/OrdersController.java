package com.example.capstone3.Controller;

import com.example.capstone3.DTO.OrdersDTO;
import com.example.capstone3.Model.Orders;
import com.example.capstone3.Service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/event/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;
    @GetMapping("/get")
    public ResponseEntity getAllOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.getAllOrdres());
    }
    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody @Valid OrdersDTO orders){
        ordersService.addOrder(orders);
        return ResponseEntity.status(HttpStatus.OK).body("Order added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id,@RequestBody @Valid OrdersDTO orders){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.updateOrder(id,orders));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.deleteOrder(id));
    }

    @GetMapping("/get-event-orders/{eventid}")
    private ResponseEntity getEventOrders(@PathVariable Integer eventid){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.getEventOrders(eventid));
    }

    @PutMapping("/accept-order/{orderid}/{comid}")
    private ResponseEntity acceptOrder(@PathVariable Integer orderid, @PathVariable Integer comid){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.acceptOrder(orderid,comid));
    }

    @GetMapping("/search-place/{placeid}")
    public ResponseEntity getOrdersByPlace(@PathVariable Integer placeid){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.getOrdersByPlace(placeid));
    }

    @GetMapping("/search-status/{status}")
    public ResponseEntity getOrdersByStatus(@PathVariable String status){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.getOrdersByStatus(status));
    }
    @GetMapping("/search-after/{date}")
    public ResponseEntity ordersAfter(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.ordersAfter(date));
    }
    @GetMapping("/search-before/{date}")
    public ResponseEntity ordersBefore(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.ordersBefore(date));
    }
    @GetMapping("/search-between/{date1}/{date2}")
    public ResponseEntity ordersBetween(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date1, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date2){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.ordersBetween(date1,date2));
    }
}
