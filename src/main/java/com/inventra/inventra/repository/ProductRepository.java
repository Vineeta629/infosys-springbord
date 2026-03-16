

package com.inventra.inventra.repository;

import com.inventra.inventra.entity.Category;
import com.inventra.inventra.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategory(Category category);

}
