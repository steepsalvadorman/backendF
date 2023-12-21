package com.steep.springboot.backendjava.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.steep.springboot.backendjava.dao.entity.OrderDetailEntity;
import com.steep.springboot.backendjava.dao.entity.ProductEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private double unitPrice;
    @JsonManagedReference
    private List<OrderDetailEntity> orderDetails;
    public static ProductDTO fromEntity(ProductEntity productEntity) {
        return new ProductDTO(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getUnitPrice(),
                (List<OrderDetailEntity>) productEntity.getOrderDetails());
    }

    public ProductEntity toEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(this.id);
        productEntity.setName(this.name);
        productEntity.setUnitPrice(this.unitPrice);
        productEntity.setOrderDetails((OrderDetailEntity) this.orderDetails);
        return productEntity;
    }
}

