package com.steep.springboot.backendjava.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.steep.springboot.backendjava.dao.entity.OrderDetailEntity;
import com.steep.springboot.backendjava.dao.entity.OrderEntity;
import com.steep.springboot.backendjava.dao.entity.OrderStatusEntity;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private String orderNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;
    private int numProducts;
    private double finalPrice;
    private OrderStatusEntity orderStatus;
    private List<OrderDetailEntity> orderDetails;


    public static OrderDTO fromEntity(OrderEntity orderEntity) {
        return new OrderDTO(
                orderEntity.getId(),
                orderEntity.getOrderNumber(),
                orderEntity.getDate(),
                orderEntity.getNumProducts(),
                orderEntity.getFinalPrice(),
                orderEntity.getOrderStatus(),
                orderEntity.getOrderDetails());
    }

    public OrderEntity toEntity() {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(this.id);
        orderEntity.setOrderNumber(this.orderNumber);
        orderEntity.setDate(this.date);
        orderEntity.setNumProducts(this.numProducts);
        orderEntity.setFinalPrice(this.finalPrice);
        orderEntity.setOrderDetails(this.orderDetails);
        return orderEntity;
    }

}
