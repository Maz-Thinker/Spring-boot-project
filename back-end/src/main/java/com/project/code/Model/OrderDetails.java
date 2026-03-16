package com.project.code.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonManagedReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="store_id")
    @JsonManagedReference
    private Store store;


    private Double totalPrice;

    private LocalDateTime date;

    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List orderItems;

    // Getters and Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public Store getStore() { return store; }

    public void setStore(Store store) { this.store = store; }

    public Double getTotalPrice() { return totalPrice; }

    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public List getOrderItems() { return orderItems; }

    public void setOrderItems(List orderItems) { this.orderItems = orderItems; }

    public OrderDetails() {}

    public OrderDetails(Customer customer, Store store, Double totalPrice, LocalDateTime date) { this.customer = customer; this.store = store; this.totalPrice = totalPrice; this.date = date; }



}
