package com.app.ecommerce.repository;

import com.app.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface Productrepo extends JpaRepository<Product,Long> {

    public Product findByname(String name);

    @Transactional
    @Query(value = "SELECT setval(p_get_serial_sequence('user', 'id'), (SELECT MAX(id) FROM user))", nativeQuery = true)
    void resetUserSequence();
}
