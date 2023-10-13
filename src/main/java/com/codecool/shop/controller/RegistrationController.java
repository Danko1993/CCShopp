package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.entities.Customer;
import com.codecool.shop.service.CustomerService;
import com.codecool.shop.util.PasswordSecurity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(urlPatterns = {"/register/"})
public class RegistrationController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        engine.process("customer/registration.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer;
        CustomerService customerService = new CustomerService();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            byte[] salt = PasswordSecurity.getSalt();
            String hashedPassword = PasswordSecurity.getSecurePassword(password, salt);
            customer = new Customer(email, hashedPassword, salt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try {
            customerService.addCustomer(customer);
            resp.sendRedirect("http://localhost:8080/codecoolshop_war_exploded/login/");
        } catch (RuntimeException r) {
            logger.error("User email already exists");
            doGet(req, resp);
        }
    }
}