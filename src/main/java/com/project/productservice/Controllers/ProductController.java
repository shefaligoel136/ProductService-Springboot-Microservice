package com.project.productservice.Controllers;

import com.project.productservice.Models.ProductModel;
import com.project.productservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductModel> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductModel getProduct(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    @PostMapping()
    public ProductModel addProduct(@RequestBody ProductModel product) {
        ProductModel p = new ProductModel();
        p.setTitle("new product");
        return p;
    }

    @PatchMapping("/{id}")
    public ProductModel updateProduct(@PathVariable("id") Long id, @RequestBody ProductModel product) {
        return new ProductModel();
    }

    @PutMapping("/{id}")
    public ProductModel replaceProduct(@PathVariable("id") Long id, @RequestBody ProductModel product) {
        return new ProductModel();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
    }
}
