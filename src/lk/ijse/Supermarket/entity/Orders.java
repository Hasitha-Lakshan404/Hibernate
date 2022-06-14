package lk.ijse.Supermarket.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Hasitha Lakshan
 * Project :SuperMarket_WIth_Hibernate
 * Date :6/14/2022
 * Time :3:40 AM
 */
@Entity
public class Orders {
    @Id
    private String id;
    @CreationTimestamp
    private LocalDate date;
    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderDetailsList = new ArrayList<>();


    public Orders() {
    }

    public Orders(String id) {
        this.id = id;
    }

    public Orders(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public Orders(String id, LocalDate date, Customer customer) {
        this.id = id;
        this.date = date;
        this.customer = customer;
    }

    public Orders(String id, LocalDate date, Customer customer, List<OrderDetail> orderDetailsList) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.orderDetailsList = orderDetailsList;
    }

    public List<OrderDetail> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetail> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", customer=" + customer +
                ", orderDetailsList=" + orderDetailsList +
                '}';
    }
}
