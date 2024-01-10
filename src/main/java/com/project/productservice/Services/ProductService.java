package com.project.productservice.Services;

import com.project.productservice.Exception.ProductNotExistException;
import com.project.productservice.Models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id) throws ProductNotExistException;

    List<Product> getAllProducts();

    Product addProduct(Product product);

    Product updateProduct(Long id, Product product);

    Product replaceProduct(Long id, Product product);

    void deleteProduct(Long id);

}
