package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.SupplierService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = {"/supplier/"})

public class SupplierController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        SupplierService supplierService = new SupplierService(supplierDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("ctxPath",req.getContextPath());
        try{
            context.setVariable("supplier", supplierService.getSupplier(Integer.parseInt(req.getParameter("supplier"))));
            context.setVariable("products", productService.getBy(supplierService.getSupplier(Integer.parseInt(req.getParameter("supplier")))));
        }catch (Exception e){
            context.setVariable("supplier", supplierService.getSupplier(1));
            context.setVariable("products", productService.getBy(supplierService.getSupplier(1)));
        }
        context.setVariable("categories",productCategoryDataStore.getAll());
        context.setVariable("suppliers",supplierDataStore.getAll());
        engine.process("supplier/index.html", context, resp.getWriter());

    }

}
