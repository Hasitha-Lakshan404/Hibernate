
package lk.ijse.Supermarket.util;

import lk.ijse.Supermarket.entity.Customer;
import lk.ijse.Supermarket.entity.Item;
import lk.ijse.Supermarket.entity.OrderDetail;
import lk.ijse.Supermarket.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket_WIth_Hibernate
 * Date :6/14/2022
 * Time :3:45 AM
 */

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Orders.class)
                .addAnnotatedClass(OrderDetail.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return factoryConfiguration==null? factoryConfiguration = new FactoryConfiguration():factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
