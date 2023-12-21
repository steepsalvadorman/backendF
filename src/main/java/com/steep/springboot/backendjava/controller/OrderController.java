package com.steep.springboot.backendjava.controller;

import com.steep.springboot.backendjava.controller.error_handler.ErrorResponse;
import com.steep.springboot.backendjava.dao.entity.OrderDetailEntity;
import com.steep.springboot.backendjava.dao.entity.OrderEntity;
import com.steep.springboot.backendjava.dto.OrderDTO;
import com.steep.springboot.backendjava.dto.OrderDetailDTO;
import com.steep.springboot.backendjava.dto.OrderStatusDTO;
import com.steep.springboot.backendjava.service.OrderStatusService;
import com.steep.springboot.backendjava.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {


    private final OrderService orderService;
    private final OrderStatusService orderStatusService;

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        List<OrderEntity> orders = orderService.listarTodos();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrderEntity>> getOrderById(@PathVariable Long id) {
        Optional<OrderEntity> orderDTO = orderService.obtenerUno(id);
        if (orderDTO.isPresent()) {
            return ResponseEntity.ok(orderDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderEntity orderEntity) {
        try {
            List<OrderDetailDTO> orderDetailDTOList = orderEntity.getOrderDetails().stream()
                    .map(OrderDetailDTO::fromEntity)
                    .toList();
            List<OrderDetailEntity> orderDetailEntityList = new ArrayList<>();

            for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
                OrderDetailEntity orderDetailEntity = orderDetailDTO.toEntity();
                orderDetailEntity.setOrder(orderEntity);
                orderDetailEntityList.add(orderDetailEntity);
            }

            orderEntity.setOrderDetails(orderDetailEntityList);
            OrderEntity createdOrderEntity = orderService.insertar(orderEntity);
            OrderDTO createdOrderDTO = OrderDTO.fromEntity(createdOrderEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDTO);
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "Error al procesar la solicitud.";

            if (e.getMessage() != null && !e.getMessage().isEmpty()) {
                errorMessage += " Detalles: " + e.getMessage();
            }

            ErrorResponse errorResponse = new ErrorResponse(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderEntity updatedOrder) {
        try {
            Optional<OrderEntity> existingOrderOptional = orderService.obtenerUno(id);

            if (existingOrderOptional.isPresent()) {
                OrderEntity existingOrder = existingOrderOptional.get();
                existingOrder.setOrderNumber(updatedOrder.getOrderNumber());
                existingOrder.setDate(updatedOrder.getDate());
                existingOrder.setFinalPrice(updatedOrder.getFinalPrice());
                existingOrder.setNumProducts(updatedOrder.getNumProducts());
                OrderEntity updatedOrderEntity = orderService.modificar(existingOrder);
                OrderDTO updatedOrderDTO = OrderDTO.fromEntity(updatedOrderEntity);
                return ResponseEntity.ok(updatedOrderDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "Error al procesar la solicitud.";
            if (e.getMessage() != null && !e.getMessage().isEmpty()) {
                errorMessage += " Detalles: " + e.getMessage();
            }
            ErrorResponse errorResponse = new ErrorResponse(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatusDTO newStatusDTO) {
        orderStatusService.modificar(id, newStatusDTO);
        return ResponseEntity.ok().build();
    }


}
