package com.steep.springboot.backendjava.service.impl;

import com.steep.springboot.backendjava.dao.entity.ProductEntity;
import com.steep.springboot.backendjava.dao.repository.ProductRepository;
import com.steep.springboot.backendjava.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public List<ProductEntity> listarTodos() {
        return productRepository.findAll();

    }

    @Override
    public ProductEntity obtenerUno(Long codigo) {
        return productRepository.findById(codigo).get();
    }

    @Override
    public ProductEntity insertar(ProductEntity p) {
        return productRepository.save(p);
    }

    @Override
    public ProductEntity modificar(ProductEntity p) {
        return productRepository.save(p);
    }

    @Override
    public void eliminar(Long codigo) {
        productRepository.deleteById(codigo);
    }
}
