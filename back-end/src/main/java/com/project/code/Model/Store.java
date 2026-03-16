package com.project.code.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name cannot be null") @NotBlank(message = "Name cannot be blank") private String name;
    @NotNull(message = "Address cannot be null") @NotBlank(message = "Address cannot be blank") private String address;

    @OneToMany(mappedBy = "store",fetch = FetchType.EAGER)
    @JsonBackReference("inventory-store")
    private List  inventory;


    // Getters and Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public List getInventory() { return inventory; }

    public void setInventory(List inventory) { this.inventory = inventory; }

// Constructors (if necessary) public Store() { }

    public Store(String name, String address) { this.name = name; this.address = address; }
}

