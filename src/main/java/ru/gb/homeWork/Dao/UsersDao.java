package ru.gb.homeWork.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.homeWork.SessionFactoryShop;
import ru.gb.homeWork.product.Products;
import ru.gb.homeWork.sale.Sales;
import ru.gb.homeWork.user.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ru.gb.homeWork.SessionFactoryShop.init;

@Service
public class UsersDao {
    private static SessionFactory factory;

//        public static void main(String[] args) {
//        String name = "";
//        Long id = null;
//        Users users = null;
//        try {
//            init();
//            System.out.println(findUsersById(id));
//            System.out.println(findAllUsers());
//            changeUser(users,name);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            SessionFactoryShop.shutdownShop();
//        }
//    }

    private static Users findUsersById(Long id) {
        Users users;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            users = session.get(Users.class, id);
            System.out.println(users);
            session.getTransaction().commit();
        }
        return users;
    }

    private static List<Users> findAllUsers() {
        List <Users> users = new ArrayList<>();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            users = session.createQuery("from Users").getResultList();
            System.out.println(users + "\n");
            session.getTransaction().commit();

        }
        return users;
    }

    private static void changeUser(Users user, String name) {
            try (Session session = factory.getCurrentSession()){
                session.beginTransaction();
                user = session.get(Users.class, user.getId());
                user.setName(name);
                session.getTransaction().commit();
            }
    }

}
