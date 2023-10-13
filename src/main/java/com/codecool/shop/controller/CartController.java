package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.service.CartService;
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
@WebServlet(urlPatterns = {"/cart/"})
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDao cartDataStore = CartDaoMem.getInstance();
        CartService cartService = new CartService(cartDataStore);
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        SupplierService supplierService = new SupplierService(supplierDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("ctxPath",req.getContextPath());
        context.setVariable("products",cartService.getProducts());
        context.setVariable("totalCartPrice",cartService.getTotalPrice());
        context.setVariable("currency",productService.find(1).getDefaultCurrency());
        context.setVariable("categories", productService.getAllCategories());
        context.setVariable("suppliers",supplierService.getAllSuppliers());
        engine.process("cart/index.html", context, resp.getWriter());

    }
}
