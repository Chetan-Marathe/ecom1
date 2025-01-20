package com.app.ecommerce.service;

import com.app.ecommerce.entity.Product;
import com.app.ecommerce.repository.Productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Productservice {
    @Autowired
    private Productrepo productrepo;

    public List<Product> getAllproduct(){
        return productrepo.findAll();
    }

    public Product getproductbyid(Long id){
        return productrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
    }

    public void updateproduct(Product product,Long id){
        productrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        productrepo.save(product);
    }

    public  void deleteproduct(Product product,Long id){
        productrepo.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        productrepo.deleteById(id);
    }

    public Product findbyname(String name){
        return productrepo.findByname(name);
    }

    public void createproduct( Product product ){
        productrepo.save(product);
    }

    //We are going to verify if the user rhass the same password as thta in the database
}
