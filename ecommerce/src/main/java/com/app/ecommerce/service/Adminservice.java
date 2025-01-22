package com.app.ecommerce.service;

import com.app.ecommerce.entity.Admin;
import com.app.ecommerce.entity.User;
import com.app.ecommerce.repository.Adminrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Adminservice {
    @Autowired
    private Adminrepo adminrepo;

    public List<Admin> getAlladmin(){
        return adminrepo.findAll();
    }

    public Admin getadminbyid(Long id){
        return adminrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
    }

    public void updateadmin(Admin admin,Long id){
        adminrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        adminrepo.save(admin);
    }

    public  void deleteadmin(Admin admin , Long id){
        adminrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        adminrepo.deleteById(id);
    }

    //We are going to verify if the user rhass the same password as thta in the database
    public boolean verifycredentials(String email, String password) {
        Admin admin = adminrepo.findByemail(email);

        // Check if admin is null or the password is null
        if (admin == null || admin.getPassword() == null) {
            return false; // Either admin not found or password is null
        }

        return admin.getPassword().equals(password);
    }


    public void createadmin( Admin admin ){
        adminrepo.save(admin);
    }
}
