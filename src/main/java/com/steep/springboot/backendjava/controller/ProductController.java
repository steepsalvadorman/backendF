package com.steep.springboot.backendjava.controller;

import com.steep.springboot.backendjava.dao.entity.ProductEntity;
import com.steep.springboot.backendjava.dto.ProductDTO;
import com.steep.springboot.backendjava.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductEntity> products = productService.listarTodos();
        List<ProductDTO> productsDTOs = products.stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productsDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Long id) {
        ProductEntity product = productService.obtenerUno(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        ProductEntity createdProduct = productService.insertar(product);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Long id, @RequestBody ProductEntity product) {
        if (!id.equals(product.getId())) {
            return ResponseEntity.badRequest().build();
        }
        ProductEntity updatedProduct = productService.modificar(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
