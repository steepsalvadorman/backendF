package com.steep.springboot.backendjava.service;

import com.steep.springboot.backendjava.dao.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> listarTodos();

    ProductEntity obtenerUno(Long codigo);

    ProductEntity insertar(ProductEntity p);

    ProductEntity modificar(ProductEntity p);

    void eliminar(Long codigo);
}
