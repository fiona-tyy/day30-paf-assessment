package ibf2022.assessment.paf.batch3.models;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Orders {
    private String orderId;
    private LocalDate date;
    private Integer breweryId;
    private List<Order> orders = new LinkedList<>();

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Integer getBreweryId() {
        return breweryId;
    }
    public void setBreweryId(Integer breweryId) {
        this.breweryId = breweryId;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public void addOrder(Order order){
        this.orders.add(order);
    }

    @Override
    public String toString() {
        return "Orders [orderId=" + orderId + ", date=" + date + ", breweryId=" + breweryId + ", orders=" + orders
                + "]";
    }
    
    
    
}
