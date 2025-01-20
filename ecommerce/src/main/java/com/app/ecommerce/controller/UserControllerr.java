package com.app.ecommerce.controller;

import com.app.ecommerce.entity.Admin;
import com.app.ecommerce.entity.User;
import com.app.ecommerce.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserControllerr {

    @Autowired
    private Userservice userservice;

    @GetMapping("/add/user")
    public String adduser(){
        return "addUser";
    }

    @PostMapping("/add/user")
    public String adduser(User user){
        userservice.createuser(user);
        return "Login";
    }

    @GetMapping("/update/user/{id}")
    public String updateuser(@PathVariable Long id, Model model){
        model.addAttribute("user",userservice.getuserbyid(id));
        return "UpdateUser";
    }

    @PostMapping("/update/user")
    public String updateuser(User user){
        userservice.createuser(user);
        return "/admin/home";
    }

    @DeleteMapping("/delete/user/{id}")
    public String deleteuser(@PathVariable Long id,User user){
        userservice.deleteuser(user,id);
        return "/admin/home";
    }
}
