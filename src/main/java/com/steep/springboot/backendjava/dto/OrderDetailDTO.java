package com.steep.springboot.backendjava.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.steep.springboot.backendjava.dao.entity.OrderDetailEntity;
import com.steep.springboot.backendjava.dao.entity.ProductEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {
    private Long id;
    @JsonBackReference
    private ProductDTO product;
    private int qty;
    private double totalPrice;

    public static OrderDetailDTO fromEntity(OrderDetailEntity orderDetailEntity) {
        return new OrderDetailDTO(
                orderDetailEntity.getId(),
                ProductDTO.fromEntity((ProductEntity) orderDetailEntity.getProducts()),
                orderDetailEntity.getQty(),
                orderDetailEntity.getTotalPrice()
        );
    }

    public OrderDetailEntity toEntity() {
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setId(this.id);
        orderDetailEntity.setProducts((List<ProductEntity>) this.product.toEntity());
        orderDetailEntity.setQty(this.qty);
        orderDetailEntity.setTotalPrice(this.totalPrice);
        return orderDetailEntity;
    }
}

