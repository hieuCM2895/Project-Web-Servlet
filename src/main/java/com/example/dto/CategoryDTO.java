package com.example.dto;

import com.example.model.Category;

public class CategoryDTO {

    private static CategoryDTO categoryDTO = null;

    private CategoryDTO() {}

    public static CategoryDTO getInstance() {
        if (categoryDTO == null)
            categoryDTO = new CategoryDTO();
        return categoryDTO;
    }

    public Category categoryDTO(Category category) {

        Category categoryDTO = new Category();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;

    }
}
