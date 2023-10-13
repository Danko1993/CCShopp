package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.service.CustomerService;
import com.codecool.shop.util.PasswordSecurity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
@WebServlet(urlPatterns = {"/login/"})
public class LoginController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        engine.process("customer/login.html", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String email = req.getParameter("email");
        String password = req.getParameter("password");
        CustomerService customerService = new CustomerService();

        String hashedUserPassword;
        String hashedInputPassword;

        try {
            hashedUserPassword = customerService.getCustomerByEmail(email).getHashedpassword();
            byte[] salt = customerService.getCustomerByEmail(email).getSalt();
            hashedInputPassword = PasswordSecurity.getSecurePassword(password, salt);
            if (hashedInputPassword.equals(hashedUserPassword)) {
                HttpSession session = req.getSession();
                session.setAttribute("email", email);
                resp.sendRedirect("http://localhost:8080/codecoolshop_war_exploded/");
            }
            else {
                doGet(req, resp);
            }
        } catch (NullPointerException e) {
            doGet(req, resp);
        }
    }
}
