package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class SupplierService {
    private SupplierDao supplierDao;

    public SupplierService( SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    public Supplier getSupplier(int supplierId){
        return supplierDao.find(supplierId);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAll();
    }
}
