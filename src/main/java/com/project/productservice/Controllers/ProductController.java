package com.project.productservice.Controllers;

import com.project.productservice.Commons.AuthenticationCommons;
import com.project.productservice.DTO.RoleDTO;
import com.project.productservice.DTO.UserDTO;
import com.project.productservice.Exception.ProductNotExistException;
import com.project.productservice.Models.Product;
import com.project.productservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService, AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(@RequestHeader("AuthorizationToken") String authorizationToken) {

        UserDTO userDTO = authenticationCommons.validateToken(authorizationToken);

        if (userDTO == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        boolean isAdmin = false;

        for (RoleDTO roleDTO : userDTO.getRoles()) {
            if (roleDTO.getName().equals("ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        if (!isAdmin) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        ResponseEntity<List<Product>> response = new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        System.out.println("geg" + response);
        return response;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) throws ProductNotExistException {
        return productService.getSingleProduct(id);
    }

    @PostMapping()
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }


}
