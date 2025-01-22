package com.app.ecommerce.controller;

import com.app.ecommerce.entity.Message;
import com.app.ecommerce.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactusController {
    @Autowired
    public ContactService contactService;

    @PostMapping("/send/message")
    public String sendMessage(Message message, Model model){
        contactService.createmessage(message);
        model.addAttribute("confirmation","Your Message has been Successfully send");
        return "contactpage";
    }

}
