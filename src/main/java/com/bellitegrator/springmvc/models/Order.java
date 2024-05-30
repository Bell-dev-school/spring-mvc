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
@Table(name = "orders")
public class Order implements Comparable<Order>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer weight;

    private Integer price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "deliveryman_id")
    private User deliveryman;

    @OneToMany(mappedBy = "order" , fetch=FetchType.EAGER)
    private List<OrderPoint> orderPoints;

    @Override
    public int compareTo(Order o) {
        if(this.getId() > o.getId())
            return 1;
        else if (this.getId().equals(o.getId()))
            return 0;
        return -1 ;

    }
}

