package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@WebServlet( urlPatterns = "/payment/confirmationServlet")
public class ConfirmationServletController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> parameterNames = req.getParameterNames();
        HashMap<String, String> orderDetails = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = req.getParameterValues(paramName);
            orderDetails.put(paramName, paramValues[0]);
        }
        FormValidator formValidator = new FormValidator();
        Map errorCodes = formValidator.isValid(orderDetails);
        Map detailsMap = formValidator.returnDetailsMap(orderDetails);

        if(errorCodes.isEmpty()) {
            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(detailsMap);
            System.out.println(json);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
            LocalDateTime now = LocalDateTime.now();
            String dateAndTime = dtf.format(now);
            String customerName = (String) detailsMap.get("Name");
            String path = "F:\\Codecool Projects\\Module 3.1\\Unit 5\\codecool-shop-1-java-RM4RT1N\\json-order-details\\order-details-"+customerName.replaceAll("\\s","")+"-"+dateAndTime+".json";

            try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
                out.write(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("ctxPath",req.getContextPath());
        context.setVariable("errorCodes",errorCodes);
        context.setVariable("detailsMap",detailsMap);
        engine.process("payment/confirmation.html", context, resp.getWriter());
    }
}

