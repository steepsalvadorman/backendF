package com.steep.springboot.backendjava.dto;
import com.steep.springboot.backendjava.dao.entity.OrderStatusEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDTO {

    private Long id;
    private Long orderId;
    private String status;

    public OrderStatusEntity toEntity() {
        OrderStatusEntity entity = new OrderStatusEntity();
        entity.setId(this.id);
        return entity;
    }

    public static OrderStatusDTO fromEntity(OrderStatusEntity entity) {
        OrderStatusDTO dto = new OrderStatusDTO();
        dto.setId(entity.getId());
        dto.setOrderId(entity.getOrder().getId());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
