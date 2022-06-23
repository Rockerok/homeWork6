package ru.gb.homeWork;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.homeWork.Dao.ProductsDao;
import ru.gb.homeWork.Dao.SalesDao;
import ru.gb.homeWork.Dao.UsersDao;
import ru.gb.homeWork.config.ApplicationConfiguration;
import ru.gb.homeWork.sale.Sales;
import ru.gb.homeWork.user.Users;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class HomeWorkApplication {

//	2. Для обеих сущностей создаете Dao классы. Работу с SessionFactory выносите во вспомогательный класс;
//	3. * Создаете сервис, позволяющий по id покупателя узнать список купленных им товаров, и по id товара
//	узнавать список покупателей этого товара;
//	4.** Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом.
//	ВАЖНО И ОБЯЗАТЕЛЬНО! Dao классы и сервис должны являться Spring бинами
//	(Вам нужен Spring Context без веб части). Контроллеры создавать не надо.
//	ВАЖНО! *Выкидываете *код по подготовке данных и таблиц, и делаете отдельный скрипт
//	и формируете базу заранее. Покупателей и товары в базу складываете заранее, через
//	код этого делать не надо (лишнее усложнение). SQL-скрипт прикрепите к работе.

	public static void forcePrepareData() {
		SessionFactory factory = new Configuration()
				.configure("configs/hibernate.cfg.xml")
				.buildSessionFactory();

		Session session = null;
		try {
			String sql = Files.lines(Paths.get("preparation.sql")).collect(Collectors.joining(" "));
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createNativeQuery(sql).executeUpdate();
			session.getTransaction().commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			factory.close();
			if (session != null) {
				session.close();
			}
		}
	}
	public static void main(String[] args) throws Exception {
//		forcePrepareData();
//		SpringApplication.run(HomeWorkApplication.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		UsersDao usersDao = context.getBean(UsersDao.class);
		SalesDao salesDao = context.getBean(SalesDao.class);
		ProductsDao productsDao = context.getBean(ProductsDao.class);
		Long idUser = 1L;
		Long idProd = 2L;
		List <Sales> productList = new ArrayList<>();
		List <Sales> usertList = new ArrayList<>();
		System.out.println(productList.addAll(salesDao.findSalesByIdUser(idUser))); 	// попытка получить результат товаров по Юзеру
		System.out.println(usertList.addAll(salesDao.findSalesByIdProd(idProd)));		// попытка получить юзеров по продукту
		System.out.println();
	}
}
