package com.example.service;

import com.example.dao.CategoryDAOImpl;
import com.example.dao.ProductDAOImpl;
import com.example.dto.AccountDTO;
import com.example.dto.CategoryDTO;
import com.example.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    private CategoryDTO categoryDTO = CategoryDTO.getInstance();
    private CategoryDAOImpl categoryDAO = CategoryDAOImpl.getInstance();

    private static CategoryService categoryService = null;

    private CategoryService() {}

    public static CategoryService getInstance() {
        if (categoryService == null)
            categoryService = new CategoryService();
        return categoryService;
    }

    public Category findCategoryById(int categoryId) throws Exception {
        return categoryDTO.categoryDTO(categoryDAO.findById(Category.class, categoryId));
    }

    public List<Category> findAllCategory() throws Exception {

        List<Category> listOfCategory = categoryDAO.findAll(Category.class);
        List<Category> listOfCategoryDTO = new ArrayList<>();
        for (Category category : listOfCategory) {
            listOfCategoryDTO.add(categoryDTO.categoryDTO(category));
        }
        return listOfCategoryDTO;

    }

}
