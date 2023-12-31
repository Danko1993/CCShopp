package com.codecool.shop.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

/**
 * Thymeleaf configuration.
 */
@WebListener
public class ThymeleafConfig implements ServletContextListener {

    @Override
    public void contextInitialized(jakarta.servlet.ServletContextEvent sce) {
        JakartaServletWebApplication application = JakartaServletWebApplication.buildApplication(sce.getServletContext());
        sce.getServletContext().setAttribute("web_app", application);
        TemplateEngine engine = templateEngine(application);
        TemplateEngineUtil.storeTemplateEngine(sce.getServletContext(), engine);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private TemplateEngine templateEngine(IWebApplication servletContext) {
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(templateResolver(servletContext));
        return engine;
    }

    private ITemplateResolver templateResolver(IWebApplication application) {
        WebApplicationTemplateResolver resolver = new WebApplicationTemplateResolver(application);
        resolver.setPrefix("/templates/");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

}