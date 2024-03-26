package com.zoo_shop.service;

import com.zoo_shop.model.Category;
import com.zoo_shop.model.ProductCategory;
import com.zoo_shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    public List<Category> getCategoriesByIds(List<Long> categoryIds) {
        if (categoryIds == null) {
            return Collections.emptyList();
        }
        return categoryRepository.findAllById(categoryIds);
    }


    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public void saveCategories(List<Category> categories) {
        categoryRepository.saveAll(new ArrayList<>(categories));
    }

}
