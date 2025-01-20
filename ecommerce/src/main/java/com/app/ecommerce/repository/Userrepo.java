package com.app.ecommerce.repository;

import com.app.ecommerce.entity.Admin;
import com.app.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepo extends JpaRepository<User,Long> {

    public User findByemail(String email);
}
