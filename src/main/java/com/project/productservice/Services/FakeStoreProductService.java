package com.project.productservice.Services;

import com.project.productservice.DTO.FakeStoreProductDTO;
import com.project.productservice.Exception.ProductNotExistException;
import com.project.productservice.Models.Category;
import com.project.productservice.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    private Product convertFakeStoreProductServiceToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setTitle((fakeStoreProductDTO.getTitle()));
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setId(fakeStoreProductDTO.getId());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImageUrl(fakeStoreProductDTO.getImageUrl());
        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
//        int b = 1 / 0;
        FakeStoreProductDTO productDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
        if (productDTO != null) {
            return convertFakeStoreProductServiceToProduct(productDTO);
        }
        throw new ProductNotExistException("Product not found" + " " + id);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        FakeStoreProductDTO[] fakeStoreResponse = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDTO[].class);
        for (FakeStoreProductDTO response : fakeStoreResponse) {
            products.add(convertFakeStoreProductServiceToProduct(response));
        }
        return products;

    }

    @Override
    public Product addProduct(Product product) {
        try {
            FakeStoreProductDTO newProduct = restTemplate.postForObject("https://fakestoreapi.com/products",
                    product,
                    FakeStoreProductDTO.class);
            assert newProduct != null;
            return convertFakeStoreProductServiceToProduct(newProduct);
        } catch (Exception e) {
            throw new RestClientException("Error updating" + e.getMessage());
        }
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        try {
            FakeStoreProductDTO fakeStoreProductDto = new FakeStoreProductDTO();
            fakeStoreProductDto.setDescription(product.getDescription());
//            fakeStoreProductDto.setImageUrl(product.getImageUrl());
            fakeStoreProductDto.setPrice(product.getPrice());
            fakeStoreProductDto.setTitle(product.getTitle());
//            fakeStoreProductDto.setCategory(product.getCategory().getName());
            restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, fakeStoreProductDto, FakeStoreProductDTO.class);
            return getSingleProduct(id);
        } catch (Exception e) {
            throw new RestClientException("Error updating" + e.getMessage());
        }
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        try {
            restTemplate.delete("https://fakestoreapi.com/products/" + id);
            return null;
        } catch (Exception e) {
            throw new RestClientException("Error replacing");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            restTemplate.delete("https://fakestoreapi.com/products/" + id);
        } catch (Exception e) {
            throw new RestClientException("Error deleting");
        }
    }

}
