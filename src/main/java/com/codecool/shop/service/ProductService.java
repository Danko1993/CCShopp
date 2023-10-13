package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;


public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }


    public List<ProductCategory> getAllCategories() {
        return productCategoryDao.getAll();
    }


    public List<Product> getBy(Supplier supplier) {
        return productDao.getBy(supplier);
    }

    public List<Product> getBy(ProductCategory productCategory) {
        return productDao.getBy(productCategory);
    }
    public Product find(int id) {
        return productDao.find(id);
    }

}
