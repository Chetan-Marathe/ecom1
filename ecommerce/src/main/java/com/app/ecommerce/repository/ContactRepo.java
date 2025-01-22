package com.app.ecommerce.repository;

import com.app.ecommerce.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Message,Long> {
}
