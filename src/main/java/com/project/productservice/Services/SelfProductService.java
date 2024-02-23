package com.project.productservice.Services;

import com.project.productservice.Exception.ProductNotExistException;
import com.project.productservice.Models.Category;
import com.project.productservice.Models.Product;
import com.project.productservice.Repositories.CategoryRepository;
import com.project.productservice.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
//@Primary
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotExistException("Product with id " + id + " does not exist's");
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        Category category = product.getCategory();
        System.out.println(category);
        if (category.getId() == null) {
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        } else {
            Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
            if (optionalCategory.isEmpty()) {
                Category savedCategory = categoryRepository.save(category);
                product.setCategory(savedCategory);
            } else {
                product.setCategory(optionalCategory.get());
            }
        }

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
