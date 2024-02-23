package com.project.productservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    @ManyToOne
    private Category category;
    private Double price;
    private String description;
    private String imageUrl;
}


// PRODUCT: CATEGORY