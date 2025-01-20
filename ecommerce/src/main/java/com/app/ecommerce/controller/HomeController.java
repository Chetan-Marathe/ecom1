package com.app.ecommerce.controller;

import com.app.ecommerce.entity.Admin;
import com.app.ecommerce.repository.Productrepo;
import com.app.ecommerce.service.Productservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private Productservice productservice;

    @GetMapping({"/home","/"})
    public String homepage(){
      return "homepage";
    }

    @GetMapping("/products")
    public String productpage( Model model ){
        model.addAttribute("ProductList",productservice.getAllproduct());
        return "ProductPage";
    }

    @GetMapping("/contact")
    public String contactpage(){
        return "contactpage";
    }

    @GetMapping("/aboutus")
    public String aboutus(){
        return "aboutus";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("admin",new Admin());
        return "Login";
    }
}
