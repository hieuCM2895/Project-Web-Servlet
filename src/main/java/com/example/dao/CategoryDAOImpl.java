package com.example.dao;

import com.example.model.Category;

public class CategoryDAOImpl extends AbstractDAO<Category, Object> {

    private static CategoryDAOImpl categoryDAO = null;

    private CategoryDAOImpl() {}

    public static CategoryDAOImpl getInstance() {
        if (categoryDAO == null)
            categoryDAO = new CategoryDAOImpl();
        return categoryDAO;
    }

}
