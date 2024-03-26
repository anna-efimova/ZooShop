package com.zoo_shop.controller;

import com.zoo_shop.model.Category;
import com.zoo_shop.model.Product;
import com.zoo_shop.model.ProductCategory;
import com.zoo_shop.repository.CategoryRepository;
import com.zoo_shop.service.CategoryService;
import com.zoo_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", Arrays.asList(ProductCategory.values()));
        return "product-list";
    }

    @GetMapping("/filtered-products")
    public String getAllProductsFiltered(@RequestParam(value = "selectedCategories", required = false) List<String> selectedCategories, Model model) {
        List<Product> filteredProducts;
        if (selectedCategories != null && !selectedCategories.isEmpty()) {
            List<ProductCategory> productCategories = selectedCategories.stream()
                    .map(ProductCategory::valueOf)
                    .collect(Collectors.toList());
            filteredProducts = productService.getProductsByCategories(productCategories);
        } else {
            filteredProducts = productService.getAllProducts();
        }
        model.addAttribute("products", filteredProducts);
        model.addAttribute("categories", Arrays.asList(ProductCategory.values()));
        return "product-list";
    }


    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", ProductCategory.values());
        return "add-product";
    }

    @PostMapping("/add-product")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addProduct(
            @ModelAttribute("product") @Valid Product product,
            BindingResult result,
            @RequestParam(value = "selectedCategories", required = false) List<String> selectedCategories,
            Model model
    ) {
        if (result.hasErrors()) {
            return "add-product";
        }
        List<Category> productCategories = selectedCategories.stream()
                .map(ProductCategory::valueOf)
                .map(tag -> {
                    var entity = new Category();
                    entity.setProduct(product);
                    entity.setTag(tag);
                    return entity;
                })
                .collect(Collectors.toList());

        product.setCategories(productCategories);
        productService.addProduct(product, product.getCategories());
        categoryService.saveCategories(product.getCategories());

        return "redirect:/products";
    }


}
