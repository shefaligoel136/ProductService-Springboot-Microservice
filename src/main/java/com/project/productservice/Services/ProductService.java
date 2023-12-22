package com.project.productservice.Services;

import com.project.productservice.Models.ProductModel;

import java.util.List;

public interface ProductService {
    ProductModel getSingleProduct(Long id);

    List<ProductModel> getAllProducts();

}
