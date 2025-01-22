package com.app.ecommerce.entity;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;


@Entity
@Table(name="admin")
public class Admin {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long id;

   public String name;

   public String email;


   @Value("qwerty")
   public String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Admin(){

    }

    public Admin(String name,String password){
        this.name=name;
        this.password=password;
    }
}
