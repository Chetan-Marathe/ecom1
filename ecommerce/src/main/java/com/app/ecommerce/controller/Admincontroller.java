package com.app.ecommerce.controller;

import com.app.ecommerce.entity.Admin;
import com.app.ecommerce.entity.Order;
import com.app.ecommerce.entity.Product;
import com.app.ecommerce.entity.User;
import com.app.ecommerce.service.Adminservice;
import com.app.ecommerce.service.Orderservice;
import com.app.ecommerce.service.Productservice;
import com.app.ecommerce.service.Userservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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



    @GetMapping("/admin/verify/credentials")
    public String verifyCredentials(@ModelAttribute("admin") Admin admin,Model model){
        if (adminservice.verifycredentials(admin.getEmail(),admin.getPassword())) {
            model.addAttribute("admin",new Admin());
            model.addAttribute("user",new User());
            model.addAttribute("product",new Product());
            return "redirect:/admin/home";
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


    @PostMapping("/add/admin")
    public String createadmin(Admin admin){
        adminservice.createadmin(admin);
        return "redirect:/admin/home";
    }

    @GetMapping("/update/admin/{id}")
    public String update(@PathVariable Long id, Model model){
        model.addAttribute("admin",adminservice.getadminbyid(id));
        return "UpdateAdmin";
    }

    @PostMapping("/update/admin")
    public String updateAdmin(Admin admin){
        adminservice.createadmin(admin);
        return "redirect:/admin/home";
    }

    @GetMapping("/delete/admin/{id}")
    public String deleteadmin(@PathVariable Long id,Admin admin){
        adminservice.deleteadmin(admin,id);
        return "redirect:/admin/home";
    }

    @PostMapping("/user/login")
    public String userLogin(User user, RedirectAttributes redirectAttributes){
        if (userservice.verifycredentials(user.getEmail(),user.getPassword())){
            user=userservice.findbyemail(user.getEmail());
            redirectAttributes.addAttribute("userId",user.getId());
            return "redirect:/user/home";
        }
        redirectAttributes.addAttribute("error","Invalid email or password");
        return "Login";
    }

    @PostMapping("/place/order")
    public String placeOrder(Order order, Long userId, RedirectAttributes redirectAttributes) {
        double totalAmount = order.getPrice() * order.getQuantity();
        order.setAmount(totalAmount);
        order.setDate(new Date());

        User user = userservice.getuserbyid(userId);
        order.setUser(user);

        orderservice.createorder(order);

        redirectAttributes.addAttribute("userId", userId);
        redirectAttributes.addAttribute("messageSuccess", "The order has been placed!!");

        return "redirect:/user/home";
    }


    @PostMapping("/product/search")
    public String productsearch(String name, Long userId , Model model){
        Product product = productservice.findbyname(name);
        User user = userservice.getuserbyid(userId);
        model.addAttribute("orderList",orderservice.findorderbyuser(user));
        if(product!=null){
            model.addAttribute("product",product);
        }else{
            model.addAttribute("messageError","Sorry product not found");

        }
        model.addAttribute("userId",userId);

        return "BuyProductPage";
    }

    @GetMapping("/user/home")
    public String userHome(@ModelAttribute("userId") Long userId,Model model,@ModelAttribute("error") String error,@ModelAttribute ("messageSuccess") String messageSuccess) {
        User user = userservice.getuserbyid(userId);
        model.addAttribute("orderList", orderservice.findorderbyuser(user));
        if(!error.isEmpty()){
            model.addAttribute("error",error);
        }
        if(!messageSuccess.isEmpty()){
            model.addAttribute("messageSuccess",messageSuccess);
        }
        return "BuyProductPage";
    }

}
