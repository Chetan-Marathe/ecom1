package com.app.ecommerce.service;

import com.app.ecommerce.entity.Message;
import com.app.ecommerce.entity.Product;
import com.app.ecommerce.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    public ContactRepo contactRepo;

    public List<Message> getAllmessage(){
        return contactRepo.findAll();
    }

    public Message getmessagebyid(Long id){
        return contactRepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
    }

    public void updatemessage(Message message ,Long id){
        contactRepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        contactRepo.save(message);
    }

    public  void deletemessage(Message message,Long id){
        contactRepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        contactRepo.deleteById(id);
    }

    public void createmessage( Message message ){
        contactRepo.save(message);
    }
}
