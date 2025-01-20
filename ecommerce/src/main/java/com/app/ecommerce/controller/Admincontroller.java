package com.app.ecommerce.controller;

import com.app.ecommerce.entity.Admin;
import com.app.ecommerce.entity.Order;
import com.app.ecommerce.entity.Product;
import com.app.ecommerce.entity.User;
import com.app.ecommerce.service.Adminservice;
import com.app.ecommerce.service.Orderservice;
import com.app.ecommerce.service.Productservice;
import com.app.ecommerce.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class Admincontroller {
    @Autowired
    private Adminservice adminservice;

    @Autowired
    private Userservice userservice;

    @Autowired
    private Orderservice orderservice;

    @Autowired
    private Productservice productservice;

    private User user;

    @GetMapping("/verifyCredentials")
    public String verifycredentials(Model model, Admin admin){
        if (adminservice.verifycredentials(admin.getEmail(),admin.getPassword())) {
            return "/admin/home";
        }
        model.addAttribute("error","invalid emial or passwprd");
        return "Login";
    }

    @GetMapping("/admin/home")
    public String adminhomepage(Model model){
        model.addAttribute("adminList",adminservice.getAlladmin());
        model.addAttribute("userList",userservice.getAlluser());
        model.addAttribute("orderList",orderservice.getAllorder());
        model.addAttribute("productList",productservice.getAllproduct());
        return "AdminHomePage";
    }

    @GetMapping("/admin")
    public String createAdmin(){
        return "AddAdmin";
    }

    @PostMapping("/add/admin")
    public String createadmin(Admin admin){
        adminservice.createadmin(admin);
        return "/admin/home";
    }

    @GetMapping("/update/admin/id")
    public String updateAdmin(@PathVariable Long id,Model model){
        model.addAttribute("admin",adminservice.getadminbyid(id));

        return "updateAdmin";
    }

    @PostMapping("/update/admin")
    public String updateAdmin(Admin admin){
        adminservice.createadmin(admin);
        return "/admin/home";
    }

    @DeleteMapping("/delete/admin/{id}")
    public String deleteadmin(@PathVariable Long id,Admin admin){
        adminservice.deleteadmin(admin,id);
        return "/admin/home";
    }

    @GetMapping("/user/login")
    public String userLogin(User user, Model model){
        if (userservice.verifycredentials(user.getEmail(),user.getPassword())){
            user=userservice.findbyemail(user.getEmail());
            model.addAttribute("orderList",orderservice.findorderbyuser(user));
            return "ProductPage";
        }
        model.addAttribute("error","invalid emial or passwprd");
        return "Login";
    }

    @GetMapping("/place/order")
    public String placeOrder(Order order, Model model){
        double totalamount = order.getPrice()*order.getQuantity();
        order.setAmount(totalamount);
        order.setUser(user);
        order.setDate(new Date());
        orderservice.createorder(order);
        model.addAttribute("amount",totalamount);
        return "OrderStatus";
    }

    @GetMapping("/product/search")
    public String productsearch(String name, Model model){
        Product product = productservice.findbyname(name);
        if(product!=null){
            model.addAttribute("orderList",orderservice.findorderbyuser(user));
            model.addAttribute("product",product);
            return "Productpage";
        }
        model.addAttribute("error","Sorry product not found");
        model.addAttribute("orderList",orderservice.findorderbyuser(user));
        return "ProductPage";
    }
}
