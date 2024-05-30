package com.bellitegrator.springmvc.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    public enum Role {
        MANAGER, CUSTOMER, DELIVERYMAN, ADMIN
    }

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String telNumber;

    @Column(unique = true)
    private String email;

    private String hashPassword;

    @OneToMany(mappedBy = "customer")
    private List<Order> customerOrders;

    @OneToMany(mappedBy = "deliveryman")
    private List<Order> deliverymanOrders;
}

