import lk.ijse.Supermarket.entity.Customer;
import lk.ijse.Supermarket.entity.Item;
import lk.ijse.Supermarket.entity.OrderDetail;
import lk.ijse.Supermarket.entity.Orders;
import lk.ijse.Supermarket.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket_WIth_Hibernate
 * Date :6/14/2022
 * Time :3:20 AM
 */

public class AppInitializer {
    public static void main(String[] args) throws IOException {

        //Customer
        Customer customer1 = new Customer("C001", "Hasitha", "Bandaragama", 60000);
        Customer customer2 = new Customer("C002", "Lakshan", "Piliyandala", 35000);
        Customer customer3 = new Customer("C003", "Jenifer", " UK", 800000);

        //Item
        Item item1 = new Item("I001", "SunLight", 160, 500);
        Item item2 = new Item("I002", "Life-Boy", 195, 250);
        Item item3 = new Item("I003", "LUX", 180, 600);

        //Order
        Orders order1 = new Orders("OR-001", customer1);
        Orders order2 = new Orders("OR-002", customer1);
        Orders order3 = new Orders("OR-003", customer2);
        Orders order4 = new Orders("OR-004", customer2);

        customer1.getOrderList().add(order1);
        customer1.getOrderList().add(order2);
        customer2.getOrderList().add(order3);
        customer2.getOrderList().add(order4);

        //OrderDetails
        OrderDetail orderDetail1 = new OrderDetail("OD-001", order1, item1, 15, item1.getUnitPrice());
        OrderDetail orderDetail2 = new OrderDetail("OD-002", order1, item1, 2, item1.getUnitPrice());
        OrderDetail orderDetail3 = new OrderDetail("OD-003", order1, item2, 17, item2.getUnitPrice());
        OrderDetail orderDetail4 = new OrderDetail("OD-004", order2, item2, 5, item2.getUnitPrice());
        OrderDetail orderDetail5 = new OrderDetail("OD-005", order2, item1, 7, item1.getUnitPrice());
        OrderDetail orderDetail6 = new OrderDetail("OD-006", order2, item2, 3, item2.getUnitPrice());


        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        /** ===== Customer ====  */

        //Create-Save
        session.save(customer1);
        session.save(customer2);
        session.save(customer3);

        //Read
        Customer c1 = session.get(Customer.class, "C003");
        System.out.println(c1.getId() + " - " + c1.getName() + " - " + c1.getSalary() + " - " + c1.getAddress());

        //Update
        customer3.setAddress("LA");
        session.update(customer3);

        //Delete
        session.delete(session.get(Customer.class, "C003"));


        /** ==== Item ====  */

        //Create-Save
        session.save(item1);
        session.save(item2);
        session.save(item3);

        //Read
        Item i1 = session.get(Item.class, "I003");
        System.out.println(i1.getCode() + " - " + i1.getDescription() + " - " + i1.getUnitPrice() + " - " + i1.getQtyOnHand());

        //Update
        item1.setDescription("Baby-Soap");
        session.update(item1);

        //Delete
        session.delete(session.get(Item.class, "I003"));


        /** ===== ORDER ===== */

        //Save
        session.save(order1);
        session.save(order2);
        session.save(order3);
        session.save(order4);

        //Read
        Orders o1 = session.get(Orders.class, "OR-003");
        System.out.println(o1.getId() + " - " + o1.getDate());

        //Delete
        session.delete(o1);


        /** ===== ORDER DETAILS ==== */

        //Save
        session.save(orderDetail1);
        session.save(orderDetail2);

        session.save(orderDetail3);
        session.save(orderDetail4);

        session.save(orderDetail5);
        session.save(orderDetail6);

        //Read
        OrderDetail orderDetail = session.get(OrderDetail.class, "OD-002");
        System.out.println(orderDetail.getId() + " - " + orderDetail.getUnitPrice() + " - " + orderDetail.getQty());

        //Update
        orderDetail3.setUnitPrice(100);
        session.update(orderDetail3);

        session.delete(orderDetail5);

        transaction.commit();
        session.close();

    }
}
