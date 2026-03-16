package com.inventra.inventra.repository;

import com.inventra.inventra.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem, Long> {

}
