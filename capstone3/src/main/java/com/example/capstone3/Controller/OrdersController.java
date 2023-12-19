package com.example.capstone3.Controller;

import com.example.capstone3.DTO.OrdersDTO;
import com.example.capstone3.Model.Orders;
import com.example.capstone3.Service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    public ResponseEntity getAllOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.getAllOrdres());
    }
    @PostMapping("/add/")
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
}
