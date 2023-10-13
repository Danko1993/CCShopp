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
import jakarta.servlet.ServletContext;
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

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

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
            context.setVariable("category", productService.getProductCategory(Integer.parseInt(req.getParameter("category"))));
            context.setVariable("products", productService.getBy(productService.getProductCategory(Integer.parseInt(req.getParameter("category")))));
        }catch (Exception e){
            context.setVariable("category", productService.getProductCategory(7));
            context.setVariable("products", productService.getBy(productService.getProductCategory(7)));
        }
        context.setVariable("categories", productService.getAllCategories());
        context.setVariable("suppliers",supplierService.getAllSuppliers());
        engine.process("product/index.html", context, resp.getWriter());

    }

}
