package ru.gb.homeWork.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ru.gb.homeWork.SessionFactoryShop;
import ru.gb.homeWork.product.Products;

import java.util.ArrayList;
import java.util.List;

import static ru.gb.homeWork.SessionFactoryShop.init;

@Service
public class ProductsDao {
    private static SessionFactory factory;

//    public static void main(String[] args) {
//        Products product = null;
//        Long id = null;
//        try {
//            init();
//            System.out.println(findProdById(id));
//            System.out.println(findAllProd());
//            deleteProdById(id);
//            changeProdPrice(product,1000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            SessionFactoryShop.shutdownShop();
//        }
//    }

    private static Products findProdById(Long id)throws Exception {
        Products product;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            product = session.get(Products.class, id);
            System.out.println(product);
            session.getTransaction().commit();
        }
        return product;
    }
    private static List<Products> findAllProd() throws Exception{
        List<Products> prodRepo = new ArrayList<>();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            prodRepo = session.createQuery("from Products").getResultList();
            System.out.println(prodRepo + "\n");
            session.getTransaction().commit();
        }
        return prodRepo;
    }
    private static boolean deleteProdById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Products product = session.get(Products.class, id);
            session.delete(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    private static Products changeProdPrice(Products product,int price) throws Exception{
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            product = session.get(Products.class, product.getId());
            product.setPrice(price);
            session.getTransaction().commit();
        }
        return product;
    }

}
