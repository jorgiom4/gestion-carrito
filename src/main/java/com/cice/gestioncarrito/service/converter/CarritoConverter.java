package com.cice.gestioncarrito.service.converter;

import com.cice.gestioncarrito.data.entity.CarritoEntity;
import com.cice.gestioncarrito.data.entity.ProductoEntity;
import com.cice.gestioncarrito.web.dto.CarritoDTO;
import com.cice.gestioncarrito.web.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarritoConverter {

    @Autowired
    private ProductoConverter productoConverter;

    public CarritoDTO CarritoEntityToDTO(CarritoEntity carritoEntity) {
        CarritoDTO carritoDTO = null;
        carritoDTO = new CarritoDTO(
                carritoEntity.getId(),
                carritoEntity.getIdUsuario(),
                precioTotal(carritoEntity),
                productoConverter.listarProductosDTO(carritoEntity.getProductos())); //tengo que hacer el cambio de estos productos de entity a dto
        return carritoDTO;

    }


    private Double precioTotal(CarritoEntity carritoEntity) {
        Double salida = 0.0;

        for(ProductoEntity productoEntity : carritoEntity.getProductos())
            salida += productoEntity.getPrecio();

        return salida;

    }

}
