package com.project.productservice.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel {
    private Long id;
    private String title;
    private Category category;
    private double price;
    private String description;
    private String imageUrl;

}
