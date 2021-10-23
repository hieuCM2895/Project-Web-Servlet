package com.example.dto;

import com.example.model.Product;

public class ProductDTO {

    private static ProductDTO productDTO = null;

    private ProductDTO() {}

    public static ProductDTO getInstance() {
        if (productDTO == null)
            productDTO = new ProductDTO();
        return productDTO;
    }

    public Product productDTO(Product product) {

        Product productDTO = new Product();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImage(product.getImage());
        productDTO.setTitle(product.getTitle());
        productDTO.setCategory(product.getCategory());

        return productDTO;

    }
}
