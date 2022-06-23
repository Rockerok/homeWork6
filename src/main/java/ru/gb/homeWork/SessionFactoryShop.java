package ru.gb.homeWork;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class SessionFactoryShop {
    private static org.hibernate.SessionFactory factory;
    public static void init() {
        HomeWorkApplication.forcePrepareData();
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void shutdownShop() {
        factory.close();
    }

}
