package com.project.code.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonManagedReference
    private OrderDetails order;

    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonManagedReference
    private Product product;

    private Integer quantity;

    private Double price;

    // Getters and Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public OrderDetails getOrder() { return order; }

    public void setOrder(OrderDetails order) { this.order = order; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    public Integer getQuantity() { return quantity; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public OrderItem() {}

    public OrderItem(OrderDetails order, Product product, Integer quantity, Double price) { this.order = order; this.product = product; this.quantity = quantity; this.price = price; }
}

