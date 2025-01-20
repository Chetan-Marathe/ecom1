package com.app.ecommerce.controller;

import com.app.ecommerce.entity.Product;
import com.app.ecommerce.service.Productservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private Productservice productservice;

    @GetMapping("/add/product")
    public String addproduct(){
        return "AddProduct";
    }

    @PostMapping("/add/product")
    public String addproduct(Product product){
        productservice.createproduct(product);
        return "/admin/home";
    }
    @GetMapping("/update/product/{id}")
    public String updateproduct(@PathVariable Long id,Model model){
        model.addAttribute("product",productservice.getproductbyid(id));
        return "UpdateProduct";
    }

    @PostMapping("/update/product")
    public String Updateproduct(Product product){
        productservice.updateproduct(product, product.getId());
        return "/admin/home";
    }

    @DeleteMapping("/delete/product/{id}")
    public String delteproduct(@PathVariable Long id,Product product){
       productservice.deleteproduct(product,id);
       return "/admin/home";
    }


}
