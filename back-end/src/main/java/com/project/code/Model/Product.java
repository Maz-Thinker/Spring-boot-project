package com.project.code.Model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name="product",uniqueConstraints = @UniqueConstraint(columnNames = "sku"))
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @NotNull(message = "Name cannot be null") private String name;

    @NotNull(message = "Name cannot be null") private String category;

    @NotNull(message = "Name cannot be null") private Double price;

    @NotNull(message = "Name cannot be null") private String sku;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonManagedReference("inventory-product")
    private List inventory;


    // Getters and Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public String getSku() { return sku; }

    public void setSku(String sku) { this.sku = sku; }

    public List getInventory() { return inventory; }

    public void setInventory(List inventory) { this.inventory = inventory; }

    public Product() { }

    public Product(String name, String category, Double price, String sku) { this.name = name; this.category = category; this.price = price; this.sku = sku; }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", sku='" + sku + '\'' +
                '}';
    }




}


