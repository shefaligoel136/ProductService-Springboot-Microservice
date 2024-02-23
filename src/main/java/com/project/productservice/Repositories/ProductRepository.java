package com.project.productservice.Repositories;

import com.project.productservice.Models.Category;
import com.project.productservice.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);

    List<Product> findByCategory_Id(Long id);

    Optional<Product> findById(Long id);

    Product save(Product product);

}
