package com.zoo_shop.service;

import com.zoo_shop.model.Category;
import com.zoo_shop.model.Product;
import com.zoo_shop.model.ProductCategory;
import com.zoo_shop.repository.CategoryRepository;
import com.zoo_shop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products != null) {
            logger.info("Retrieved {} products from the database.", products.size());
        } else {
            logger.warn("No products found in the database.");
        }
        return products;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories != null) {
            logger.info("Retrieved {} categories from the database.", categories.size());
        } else {
            logger.warn("No categories found in the database.");
        }
        return categories;
    }

    public List<Product> getProductsByCategories(List<ProductCategory> categories) {
        List<Category> categoryEntities = categoryRepository.findByTagIn(categories);
        Set<Long> productIds = categoryEntities.stream()
                .map(Category::getProduct)
                .map(Product::getId)
                .collect(Collectors.toSet());
        return productRepository.findAllById(productIds);
    }

    @Transactional
    public void addProduct(Product product, List<Category> categories) {
        try {
            List<Category> savedCategories = new ArrayList<>();
            for (Category category : categories) {
                if (category.getId() == null) {
                    savedCategories.add(categoryRepository.save(category));
                } else {
                    savedCategories.add(category);
                }
            }
            product.setCategories(savedCategories);

            if (product.getId() == null) {
                productRepository.save(product);
                logger.info("Product {} successfully added with categories: {}", product.getName(), product.getCategories());
            } else {
                Optional<Product> existingProductOptional = productRepository.findById(product.getId());

                if (existingProductOptional.isPresent()) {
                    Product existingProduct = existingProductOptional.get();
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setCategories(savedCategories);

                    productRepository.save(existingProduct);
                    logger.info("Product {} successfully updated with categories: {}", existingProduct.getName(), existingProduct.getCategories());
                } else {
                    logger.error("Product with id {} does not exist", product.getId());
                }
            }
        } catch (Exception e) {
            logger.error("Error adding/updating product {}: {}", product.getName(), e.getMessage());
        }
    }

}
