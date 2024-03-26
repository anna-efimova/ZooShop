package com.zoo_shop.model;

import com.zoo_shop.converter.ProductCategoryConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //(targetEntity = Product.class)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;


    @Column(nullable = false)
    private ProductCategory tag;


    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductCategory getTag() {
        return tag;
    }

    public void setTag(ProductCategory tag) {
        this.tag = tag;
    }
}


