package com.app.ecommerce.repository;

import com.app.ecommerce.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Adminrepo extends JpaRepository<Admin,Long> {

    public Admin findByemail(String email);

}
