package com.codecool.shop.config;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.entities.Category;
import com.codecool.shop.entities.SupplierDataBase;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.util.HashMap;

@WebListener
public class Initializer implements ServletContextListener {



    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Session session= sessionFactory().openSession();
        session.beginTransaction();
        SupplierDataBase amazon= new SupplierDataBase("Amazon");
        SupplierDataBase lenovo= new SupplierDataBase("Lenovo");
        SupplierDataBase ikea= new SupplierDataBase("Ikea");
        SupplierDataBase samsung= new SupplierDataBase("Samsung");
        SupplierDataBase apple= new SupplierDataBase("Apple");
        SupplierDataBase rebel= new SupplierDataBase("Rebel");
        SupplierDataBase forceArmy= new SupplierDataBase("Force Army");

        Category tablet = new Category("tablet");
        Category mascots = new Category("Mascots");
        Category computers = new Category("Computers");
        Category phones = new Category("Phones");
        Category boardGames = new Category("Board Games");
        Category militaryForce = new Category("Military Force");
        Category hitsOfTheMonth = new Category("Hits of the month");

        session.persist(amazon);
        session.persist(lenovo);
        session.persist(ikea);
        session.persist(samsung);
        session.persist(apple);
        session.persist(rebel);
        session.persist(forceArmy);
        session.persist(tablet);
        session.persist(mascots);
        session.persist(computers);
        session.persist(phones);
        session.persist(boardGames);
        session.persist(militaryForce);
        session.persist(hitsOfTheMonth);
        session.getTransaction().commit();
        session.clear();
//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
//        CartDao cartDataStore = CartDaoMem.getInstance();


        //setting up a new supplier
//        Supplier amazon = new Supplier("Amazon", "Digital content and services");
//        supplierDataStore.add(amazon);
//        Supplier lenovo = new Supplier("Lenovo", "Computers");
//        supplierDataStore.add(lenovo);
//        Supplier ikea= new Supplier("Ikea", "Everything to house");
//        supplierDataStore.add(ikea);
//        Supplier samsung= new Supplier("Samsung", "Electronics");
//        supplierDataStore.add(samsung);
//        Supplier apple= new Supplier("Apple", "Computers and smartphones");
//        supplierDataStore.add(apple);
//        Supplier rebel= new Supplier("Rebel", "Boardgames");
//        supplierDataStore.add(rebel);
//        Supplier forceArmy= new Supplier("Force Army", "Military");
//        supplierDataStore.add(forceArmy);

        //setting up a new product category
//        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
//        productCategoryDataStore.add(tablet);
//        ProductCategory mascots = new ProductCategory("Mascots", "Mascots", "Memetic mascots for everyone");
//        productCategoryDataStore.add(mascots);
//        ProductCategory computers = new ProductCategory("Computers", "computers", "Just computers");
//        productCategoryDataStore.add(computers);
//        ProductCategory phones = new ProductCategory("Phones", "computers", "Just computers");
//        productCategoryDataStore.add(phones);
//        ProductCategory boardGames = new ProductCategory("Board Games", "Games", "Have a great time with our board games and your friends.");
//        productCategoryDataStore.add(boardGames);
//        ProductCategory militaryForce = new ProductCategory("Military Force", "Military", "Very good tanks but better than other shops with tanks.");
//        productCategoryDataStore.add(militaryForce);
//        ProductCategory hitsOfTheMonth = new ProductCategory("Hits of the month", "All", "This month's top products");
//        productCategoryDataStore.add(hitsOfTheMonth);


        //setting up products and printing it
//        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49"), "USD",
//                "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.",
//                tablet, amazon,"static/img/product_1.jpg"));
//        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD",
//                "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.",
//                tablet, lenovo,"static/img/product_2.jpg"));
//        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD",
//                "Amazon's latest Fire HD 8 tablet is a great value for media consumption.",
//                tablet, amazon,"static/img/product_3.jpg"));
//
//        productDataStore.add(new Product("T-34", new BigDecimal("100000000"), "USD",
//                "Fantastic price. Large equipment. Good tank controls. Helpful technical support.",
//                militaryForce,forceArmy,"static/img/product_4.jpg"));
//        productDataStore.add(new Product("Blahaj", new BigDecimal("20"), "USD",
//                "Best memetic mascot ever.Buy and find out.",
//                mascots,ikea,"static/img/product_5.jpg"));
//        productDataStore.add(new Product("Blavingad", new BigDecimal("22"), "USD",
//                "Best friend blahaj mascot.Don't leave blahaj alone.",
//                mascots,ikea,"static/img/product_6.jpg"));
//        productDataStore.add(new Product("Smaslug", new BigDecimal("25"), "USD",
//                "Smaslug is a combination of dog and mascot. Two in one.",
//                mascots,ikea,"static/img/product_7.jpg"));
//        productDataStore.add(new Product("S23 Ultra", new BigDecimal("1900"), "USD",
//                "Best smartphone from samsung. Try and have fun",
//                phones,samsung,"static/img/product_8.jpg"));
//        productDataStore.add(new Product("Iphone 14 PRO MAX", new BigDecimal("2500"), "USD",
//                "The future is today, check out our new phone, which has everything you need. ",
//                phones,apple,"static/img/product_9.jpg"));
//        productDataStore.add(new Product("MacBook PRO M2", new BigDecimal("4500"), "USD",
//                "The future is today, check out our new personal computer, which has everything you need and feel free ",
//                computers,apple,"static/img/product_10.jpg"));
//        productDataStore.add(new Product("Ipad PRO", new BigDecimal("4500"), "USD",
//                "The future is today, take ipad and don't worry about space",
//                tablet,apple,"static/img/product_11.jpg"));
//        productDataStore.add(new Product("Galaxy Tab S8U Ultra", new BigDecimal("1649"), "USD",
//                "Who said you have to carry an entire computer to work comfortably.",
//                tablet,samsung,"static/img/product_12.jpg"));
//        productDataStore.add(new Product("Terraforming Mars", new BigDecimal("38"), "USD",
//                "Become a coloniser and terraform mars.",
//                boardGames,rebel,"static/img/product_13.jpg"));
//        productDataStore.add(new Product("Ticket to ride", new BigDecimal("33"), "USD",
//                "Collect tickets and create the longest route on the map.",
//                boardGames,rebel,"static/img/product_14.jpg"));
//        productDataStore.add(new Product("Everdell", new BigDecimal("38"), "USD",
//                "Have an adventure with the heroes of the forest community.",
//                boardGames,rebel,"static/img/product_15.jpg"));
//        productDataStore.add(new Product("M1A2 Abrams", new BigDecimal("150000000"), "USD",
//                "Better firing range from eastern European units. The best tank on the market.",
//                militaryForce,forceArmy,"static/img/product_16.jpg"));
//        productDataStore.add(new Product("F-35 C", new BigDecimal("798234000"), "USD",
//                "Fighter aircraft adapted for vertical take-off with full armament.",
//                militaryForce,forceArmy,"static/img/product_17.jpg"));
//        productDataStore.add(new Product("F-35 C", new BigDecimal("798234000"), "USD",
//                "Fighter aircraft adapted for vertical take-off with full armament.",
//                hitsOfTheMonth,forceArmy,"static/img/product_17.jpg"));
//        productDataStore.add(new Product("Blahaj", new BigDecimal("20"), "USD",
//                "Best memetic mascot ever.Buy and find out.",
//                hitsOfTheMonth,ikea,"static/img/product_5.jpg"));
//        productDataStore.add(new Product("MacBook PRO M2", new BigDecimal("4500"), "USD",
//                "The future is today, check out our new personal computer, which has everything you need and feel free ",
//                hitsOfTheMonth,apple,"static/img/product_10.jpg"));

    }
    public static SessionFactory sessionFactory(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException(e);
        }
    }
}
