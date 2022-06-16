package lk.ijse.Supermarket.util;

import lk.ijse.Supermarket.entity.Customer;
import lk.ijse.Supermarket.entity.Item;
import lk.ijse.Supermarket.entity.OrderDetail;
import lk.ijse.Supermarket.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket_WIth_Hibernate
 * Date :6/14/2022
 * Time :3:45 AM
 */

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

   private FactoryConfiguration() {
       Map<String, String> properties = new HashMap<>();
       properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
       properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/HibernateSuperMarket?createDatabaseIfNotExist=true");
       properties.put("hibernate.connection.username", "root");
       properties.put("hibernate.connection.password", "1234");
       properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
       properties.put("hibernate.show_sql", "true");
       properties.put("hibernate.hbm2ddl.auto", "update");
       properties.put("hibernate,format_sql","true");

       ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
       MetadataSources metadataSources = new MetadataSources(serviceRegistry).addAnnotatedClass(Customer.class)
               .addAnnotatedClass(Item.class)
               .addAnnotatedClass(Orders.class)
               .addAnnotatedClass(OrderDetail.class);

       Metadata metaData = metadataSources.buildMetadata();
       sessionFactory = metaData.buildSessionFactory();
   }

    public static FactoryConfiguration getInstance() throws IOException {
        return (factoryConfiguration == null) ? factoryConfiguration=new FactoryConfiguration()
                : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

}

