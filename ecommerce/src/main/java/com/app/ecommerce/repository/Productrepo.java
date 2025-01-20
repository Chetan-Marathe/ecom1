package com.app.ecommerce.repository;

import com.app.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Productrepo extends JpaRepository<Product,Long> {

    public Product findByname(String name);
}
