package com.steep.springboot.backendjava.dao.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OrderStatus")
public class OrderStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "OrderID")
    @JsonBackReference
    private OrderEntity order;

    @Column(name = "Status")
    private String status;
}

