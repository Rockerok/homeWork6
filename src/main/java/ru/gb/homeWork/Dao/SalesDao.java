package ru.gb.homeWork.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class SalesDao {
    private static SessionFactory factory;

//    public static void main(String[] args) {
//        Products product = null;
//        Long id = null;
//        Users users = null;
//        Date date_sal = new Date();
//        int price_prod = 1000;
//        try {
//            init();
//
//            System.out.println(findSalesById(id));
//            System.out.println(findAllSales());
//            deleteSalesById(id);
//            changeSalesPrice(date_sal,product,users,price_prod);
//            addSales(date_sal,product,users,price_prod);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            SessionFactoryShop.shutdownShop();
//        }
//    }

//    private static boolean addSales(Date date_sal, Products product, Users users, int price_prod) {
//        Sales sale = new Sales();
//        try (Session session = factory.getCurrentSession()) {
//            session.beginTransaction();
//            sale.setDate_sales(date_sal);
//            sale.setProducts(product);
//            sale.set
//            sale.setPrice_prod(price_prod);
//            session.getTransaction().commit();
//            return true;
//        } catch (Exception e){
//            System.out.println(e);
//            return false;
//        }
//    }

    private static Sales changeSalesPrice(Date date_sal, Products product, Users users, int price_prod) {
        Sales sale;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            sale = session.get(Sales.class, product.getId());
            sale.setPrice_prod(price_prod);
            session.getTransaction().commit();
        }
        return sale;
    }

    private static boolean deleteSalesById(Long id) {
        return false;
    }

    private static List <Sales> findAllSales()throws Exception {
        List <Sales> sales = new ArrayList<>();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            sales = session.createQuery("from Sales").getResultList();
            System.out.println(sales + "\n");
            session.getTransaction().commit();
        }
        return sales;
    }

    public static List <Sales> findSalesByIdUser(Long idUser) throws Exception {
        List <Sales> sales = new ArrayList<>();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            sales = session.createQuery("select\n" +
                    "p.title,\n" +
                    "from \n" +
                    "shop.sales as s\n" +
                    "left join shop.users as u on s.users_id = u.id\n" +
                    "left join shop.products as p on s.product_id=p.id\n" +
                    "where s.users_id = "+idUser).getResultList();
            session.getTransaction().commit();
        }
        return sales;
    }
    public static List <Sales> findSalesByIdProd(Long idProd) throws Exception {
        List <Sales> sales = new ArrayList<>();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            sales = session.createQuery("select\n" +
                    "p.title,\n" +
                    "from \n" +
                    "shop.sales as s\n" +
                    "left join shop.users as u on s.users_id = u.id\n" +
                    "left join shop.products as p on s.product_id=p.id\n" +
                    "where s.users_id = "+idProd).getResultList();
            session.getTransaction().commit();
        }
        return sales;
    }
}
