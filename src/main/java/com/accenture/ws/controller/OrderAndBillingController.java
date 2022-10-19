package com.accenture.ws.controller;


import com.accenture.ws.entity.CafeClerk;
import com.accenture.ws.entity.Order;
import com.accenture.ws.impl.DiscountedBill;
import com.accenture.ws.impl.OrderBill;
import com.accenture.ws.impl.RegularBill;
import com.accenture.ws.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin
public class OrderAndBillingController {

    @Autowired
    OrderRepository orderRepo;

    CafeClerk clerk;

    public OrderAndBillingController() {

    }

    public OrderAndBillingController(CafeClerk clerk) {
        this.clerk = clerk;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @PostMapping("/order")
    public void addOrder(@RequestBody Order order) {

        Order addedOrder = new Order();

        addedOrder.setOrderName(order.getOrderName());
        addedOrder.setPrice(order.getPrice());
        addedOrder.setDiscounted(order.isDiscounted());
        if (order.isDiscounted()) {
            addedOrder.setIsDiscountPercentage(5.0);
        } else {
            addedOrder.setIsDiscountPercentage(0);
        }

        orderRepo.save(addedOrder);
    }

    @PutMapping("/order/{id}")
    public void updateOrder(@PathVariable("id") Long id, @RequestBody Order order) {

       Optional<Order> editOrder = orderRepo.findById(id);

       Order selectedOrder = editOrder.get();

        selectedOrder.setOrderName(order.getOrderName());
        selectedOrder.setPrice(order.getPrice());
        selectedOrder.setDiscounted(order.isDiscounted());
        if (order.isDiscounted()) {
            selectedOrder.setIsDiscountPercentage(5.0);
        } else {
            selectedOrder.setIsDiscountPercentage(0);
        }

        orderRepo.save(selectedOrder);

    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable("id") Long id){
        orderRepo.deleteById(id);
    }

    @GetMapping("/regular-bill")
    public ResponseEntity<?> getTotalRegularBill() {
        List<Order> listOrder = orderRepo.findAll();
        RegularBill regularBill = new RegularBill();
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("data", regularBill.getTotalBill(listOrder));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("discounted-bill")
    public ResponseEntity<?> getTotalDiscountedBill() {
        List<Order> listOrder = orderRepo.findAll();
        DiscountedBill discountedBill = new DiscountedBill();
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("data", discountedBill.getTotalBill(listOrder));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
