package com.example.practica2_corte2.service.serviceImpl;


import com.example.practica2_corte2.model.Producto;
import com.example.practica2_corte2.service.ProductoService;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computacion", 175000),
                new Producto(2L, "mesa escritorio", "oficina", 100000),
                new Producto(3L, "teclado mecanico", "computacion", 40000));
    }
}
