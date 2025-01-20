package com.app.ecommerce.service;

import com.app.ecommerce.entity.Order;
import com.app.ecommerce.entity.User;
import com.app.ecommerce.repository.Orderrepo;
import com.app.ecommerce.repository.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Orderservice {

    @Autowired
    private Orderrepo orderrepo;

    public List<Order> getAllorder(){
        return orderrepo.findAll();
    }

    public Order getorderbyid(Long id){
        return orderrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
    }

    public void updateorder(Order order,Long id){
        orderrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        orderrepo.save(order);
    }

    public  void deleteorder(Order order , Long id){
        orderrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        orderrepo.deleteById(id);
    }

    public void createorder( Order order ){
        orderrepo.save(order);
    }

    public List<Order> findorderbyuser(User user){
        return orderrepo.findByUser(user);
    }
}
