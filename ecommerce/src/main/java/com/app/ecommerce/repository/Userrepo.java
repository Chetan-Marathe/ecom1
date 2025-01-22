package com.app.ecommerce.repository;

import com.app.ecommerce.entity.Admin;
import com.app.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface Userrepo extends JpaRepository<User,Long> {

    public User findByemail(String email);

    @Transactional
    @Query(value = "SELECT setval(pg_get_serial_sequence('user', 'id'), (SELECT MAX(id) FROM user))", nativeQuery = true)
    void resetUserSequence();
}
