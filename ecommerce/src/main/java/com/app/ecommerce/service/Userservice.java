package com.app.ecommerce.service;

import com.app.ecommerce.entity.Admin;
import com.app.ecommerce.entity.User;
import com.app.ecommerce.repository.Adminrepo;
import com.app.ecommerce.repository.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Userservice {
    @Autowired
    private Userrepo userrepo;

    public List<User> getAlluser(){
        return userrepo.findAll();
    }

    public User getuserbyid(Long id){
        return userrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
    }

    public void updateuser(User user,Long id){
        userrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        userrepo.save(user);
    }

    public  void deleteuser(User user , Long id){
        userrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        userrepo.deleteById(id);
    }

    public void createuser( User user ){
        userrepo.save(user);
    }

    public User findbyemail(String email){
        return userrepo.findByemail(email);
    }

    //We are going to verify if the user rhass the same password as thta in the database

    public boolean verifycredentials(String email ,String password){
        User user = userrepo.findByemail(email);
        if(user.getPassword()==password){
            return true;
        }else{
            return false;
        }
    }
}
