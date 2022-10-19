package com.accenture.ws.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private long id;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "price")
    private double price;

    @Column(name = "is_discounted")
    private boolean isDiscounted;

    @Column(name = "is_discount_percentage")
    private double isDiscountPercentage;

    public Order() {
        super();
    }

    public Order(String orderName, double price, boolean isDiscounted) {
        super();
        this.orderName = orderName;
        this.price = price;
        this.isDiscounted = isDiscounted;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(boolean discounted) {
        isDiscounted = discounted;
    }

    public double getIsDiscountPercentage() {
        return isDiscountPercentage;
    }

    public void setIsDiscountPercentage(double isDiscountPercentage) {
        this.isDiscountPercentage = isDiscountPercentage;
    }
}
