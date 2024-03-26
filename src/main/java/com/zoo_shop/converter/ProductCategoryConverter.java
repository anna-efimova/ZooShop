package com.zoo_shop.converter;

import com.zoo_shop.model.ProductCategory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProductCategoryConverter implements AttributeConverter<ProductCategory, String> {

    @Override
    public String convertToDatabaseColumn(ProductCategory attribute) {
        return attribute != null ? attribute.getDisplayName() : null;
    }

    @Override
    public ProductCategory convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (ProductCategory category : ProductCategory.values()) {
            if (category.getDisplayName().equalsIgnoreCase(dbData)) {
                return category;
            }
        }


        return null;
    }
}

