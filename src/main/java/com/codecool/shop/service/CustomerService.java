package com.codecool.shop.service;

import com.codecool.shop.entities.Customer;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

import static com.codecool.shop.config.Initializer.sessionFactory;

@NoArgsConstructor
public class CustomerService {


    public void addCustomer(Customer customer){
        Session session= sessionFactory().openSession();
        session.beginTransaction();
        session.persist(customer);
        session.getTransaction().commit();
        session.clear();
    }
    public Customer getCustomerByEmail(String email){
        Session session= sessionFactory().openSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> cr = cb.createQuery(Customer.class);
        Root<Customer> root = cr.from(Customer.class);
        cr.select(root).where(cb.equal(root.get("email"),email));
        Query query = session.createQuery(cr);
        query.setMaxResults(1);
        List<Customer> result = query.getResultList();
        int id = result.get(0).getID();

//        int id = session.createQuery("select id from customer where email=:email",Customer.class).setParameter("email",email).uniqueResult().getID();
        Customer customer = session.get(Customer.class,id);
        session.getTransaction().commit();
        return customer;


    }


}
