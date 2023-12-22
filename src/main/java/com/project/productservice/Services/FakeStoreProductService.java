package com.project.productservice.Services;

import com.project.productservice.DTO.FakeStoreProductDTO;
import com.project.productservice.Models.Category;
import com.project.productservice.Models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    private ProductModel convertFakeStoreProductServiceToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        ProductModel productModel = new ProductModel();
        productModel.setTitle((fakeStoreProductDTO.getTitle()));
        productModel.setPrice(fakeStoreProductDTO.getPrice());
        productModel.setId(fakeStoreProductDTO.getId());
        productModel.setDescription(fakeStoreProductDTO.getDescription());
        productModel.setImageUrl(fakeStoreProductDTO.getImageUrl());
        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());
        productModel.setCategory(category);
        return productModel;
    }

    @Override
    public ProductModel getSingleProduct(Long id) {
        FakeStoreProductDTO productDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
        return convertFakeStoreProductServiceToProduct(productDTO);
    }

    @Override
    public List<ProductModel> getAllProducts() {
        List<ProductModel> products = new ArrayList<>();
        FakeStoreProductDTO[] fakeStoreResponse = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDTO[].class);
        for (FakeStoreProductDTO response : fakeStoreResponse) {
            products.add(convertFakeStoreProductServiceToProduct(response));
        }
        return products;

    }

}
