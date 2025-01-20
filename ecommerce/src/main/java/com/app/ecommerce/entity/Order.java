package com.app.ecommerce.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Long price;

    public int quantity;

    public Date date;

    public  double amount;

    //As a user can have many order
    @ManyToOne
    @JoinColumn(name="user_Id")
    public User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order(Long id, Long price, int quantity, Date date, double amount) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
        this.amount = amount;

    }
    public Order(){

    }
}
