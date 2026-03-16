package com.inventra.inventra.repository;

import com.inventra.inventra.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderItem, Long> {

}
