package com.project.code.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Valid  @NotNull(message = "Name cannot be null")
    private String name;


    @NotNull(message = "Email cannot be null")
    private String email;


    @NotNull(message = "Phone cannot be null")
    private String phone;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List orders;




    // Getters and Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public List getOrders() { return orders; }

    public void setOrders(List orders) { this.orders = orders; }


    public Customer() {}

    public Customer(String name, String email, String phone) { this.name = name; this.email = email; this.phone = phone; }

}

