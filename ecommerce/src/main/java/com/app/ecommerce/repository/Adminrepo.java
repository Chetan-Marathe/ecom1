package com.app.ecommerce.repository;

import com.app.ecommerce.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface Adminrepo extends JpaRepository<Admin,Long> {

    public Admin findByemail(String email);


    @Transactional
    @Query(value = "SELECT setval(pg_get_serial_sequence('admin', 'id'), (SELECT MAX(id) FROM admins))", nativeQuery = true)
    void resetAdminSequence();

}
