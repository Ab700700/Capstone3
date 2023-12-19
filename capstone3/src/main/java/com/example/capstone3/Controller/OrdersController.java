package com.example.capstone3.Controller;

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
    @PostMapping("/add/{bid}/{event_id}")
    public ResponseEntity addOrder(@PathVariable Integer bid, @PathVariable Integer event_id, @RequestBody @Valid Orders orders){
        ordersService.addOrder(bid, event_id, orders);
        return ResponseEntity.status(HttpStatus.OK).body("Order added");
    }
    @PutMapping("/update/{bid}/{event_id}")
    public ResponseEntity updateOrder(@PathVariable Integer bid, @PathVariable Integer event_id , @RequestBody @Valid Orders orders){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.updateOrder(bid,event_id,orders));
    }
    @DeleteMapping("/delete/{bid}/{event_id}")
    public ResponseEntity deleteOrder(@PathVariable Integer bid , @PathVariable Integer event_id){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.deleteOrder(bid,event_id));
    }
}
