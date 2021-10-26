package com.example.service;

import com.example.dao.ProductDAOImpl;
import com.example.dto.ProductDTO;
import com.example.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private ProductDAOImpl productDAO = ProductDAOImpl.getInstance();
    private ProductDTO productDTO = ProductDTO.getInstance();

    private static ProductService productService = null;

    private ProductService() {}

    public static ProductService getInstance() {
        if (productService == null)
            productService = new ProductService();
        return productService;
    }

    public boolean deleteProductById(int productId) throws Exception {
        return productDAO.delete(productDAO.findById(Product.class, productId));
    }

    public boolean addNewProduct(Product product) throws Exception {
        return productDAO.save(product);
    }

    public boolean updateProduct(Product product) throws Exception {
        return productDAO.update(product);
    }

    public List<Product> findAllProduct() throws Exception {

        List<Product> listOfProduct = productDAO.findAll(Product.class);
        List<Product> listOfProductDTO = new ArrayList<>();
        for (Product product : listOfProduct) {
            listOfProductDTO.add(productDTO.productDTO(product));
        }
        return listOfProductDTO;

    }

    public Product findNewProduct() throws Exception {
        return productDTO.productDTO(productDAO.findNewProduct());
    }

    public Product findProductById(int productId) throws Exception {
        return productDTO.productDTO(productDAO.findById(Product.class, productId));
    }

    public Product findNewProductByCategoryId(int categoryId) throws Exception {
        return productDTO.productDTO(productDAO.findNewProductByCategoryId(categoryId));
    }

    public List<Product> searchProductByName(String txt) throws Exception {

        List<Product> listOfProduct = productDAO.searchProductByName(txt);
        List<Product> listOfProductDTO = new ArrayList<>();
        for (Product product : listOfProduct) {
            listOfProductDTO.add(productDTO.productDTO(product));
        }
        return listOfProductDTO;

    }

    public List<Product> searchProductByAccountID(int accountId, int currentNumberPage, int pageSize) throws Exception {

        List<Product> listOfProduct = productDAO.searchProductByAccountID(accountId, currentNumberPage, pageSize);
        List<Product> listOfProductDTO = new ArrayList<>();
        for (Product product : listOfProduct) {
            listOfProductDTO.add(productDTO.productDTO(product));
        }
        return listOfProductDTO;

    }

    public List<Product> searchProductByAccountID(int currentNumberPage, int pageSize) throws Exception {

        List<Product> listOfProduct = productDAO.searchProductByAccountID(currentNumberPage, pageSize);
        List<Product> listOfProductDTO = new ArrayList<>();
        for (Product product : listOfProduct) {
            listOfProductDTO.add(productDTO.productDTO(product));
        }
        return listOfProductDTO;

    }

    public List<Product> searchProductByCategoryId(int categoryId ,int currentNumberPage, int pageSize) throws Exception {

        List<Product> listOfProduct = productDAO.searchProductByCategoryId(categoryId, currentNumberPage, pageSize);
        List<Product> listOfProductDTO = new ArrayList<>();
        for (Product product : listOfProduct) {
            listOfProductDTO.add(productDTO.productDTO(product));
        }
        return listOfProductDTO;

    }

    public List<Product> findListProductByCategoryID(int categoryId) throws Exception {

        List<Product> listOfProduct = productDAO.findListProductByCategoryID(categoryId);
        List<Product> listOfProductDTO = new ArrayList<>();
        for (Product product : listOfProduct) {
            listOfProductDTO.add(productDTO.productDTO(product));
        }
        return listOfProductDTO;

    }

    public long getNumberPage(int accountId) throws Exception {
        return (long) productDAO.getNumberSellProductByAccountID(accountId);
    }

    public long getNumberPageByProduct() throws Exception {
        return (long) productDAO.getNumberSellProduct();
    }

    public long getNumberPageByProduct(int categoryId) throws Exception {
        return (long) productDAO.getNumberSellProductByCategory(categoryId);
    }

    public List<Product> searchProductByAmount(int amount) throws Exception {

        List<Product> listOfProduct = productDAO.searchProductByAmount(amount);
        List<Product> listDTO = new ArrayList<>();
        for (Product product : listOfProduct) {
            listDTO.add(productDTO.productDTO(product));
        }
        return listDTO;

    }

    public List<Product> searchLastProductByAmount(int amount) throws Exception {

        List<Product> listOfProduct = productDAO.searchLastProductByAmount(amount);
        List<Product> listDTO = new ArrayList<>();
        for (Product product : listOfProduct) {
            listDTO.add(productDTO.productDTO(product));
        }
        return listDTO;

    }

    public Object findCategoryIdByProductId(int productId) throws Exception {
        return productDAO.findCategoryIdByProductId(productId);
    }

}
