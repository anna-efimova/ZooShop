package com.zoo_shop.repository;

import com.zoo_shop.model.Category;
import com.zoo_shop.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByTagIn(List<ProductCategory> productCategories);
}
