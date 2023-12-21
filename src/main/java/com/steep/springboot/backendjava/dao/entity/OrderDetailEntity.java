package com.steep.springboot.backendjava.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "OrderDetails")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID")
    private OrderEntity order;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderDetailID")
    @JsonBackReference
    private List<ProductEntity> products;

    @Column(name = "Qty")
    private int qty;

    @Column(name = "TotalPrice")
    private double totalPrice;

}
