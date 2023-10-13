package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.ProductService;
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
@WebServlet(urlPatterns = {"/update-cart/"})
public class UpdateCartController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);
        CartDao cartDataStore = CartDaoMem.getInstance();
        CartService cartService = new CartService(cartDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        String url = req.getHeader("referer").trim();
        context.setVariable("ctxPath",req.getContextPath());
        context.setVariable("productId", productService.find(Integer.parseInt(req.getParameter("id"))));
        cartService.changeProductQuantity(productDataStore.find(Integer.parseInt(req.getParameter("id"))),Integer.parseInt(req.getParameter("quantity")));
        resp.sendRedirect(url);
    }
}
