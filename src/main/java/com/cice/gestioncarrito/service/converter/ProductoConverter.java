package com.cice.gestioncarrito.service.converter;

import com.cice.gestioncarrito.data.entity.ProductoEntity;
import com.cice.gestioncarrito.web.dto.ProductoDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductoConverter {

    public List<ProductoDTO> listarProductosDTO(List<ProductoEntity> listaProductos) {
        List<ProductoDTO> listaProductosDTO = null;

        for(ProductoEntity productoEntity : listaProductos)
            listaProductosDTO.add(productoEntityToDTO(productoEntity));

        return listaProductosDTO;
    }

    public List<ProductoEntity> listarProductosEntity (List<ProductoDTO> listaProductos) {
        List<ProductoEntity> listaProductosEntity = null;

        for(ProductoDTO productoDTO : listaProductos)
            listaProductosEntity.add(productoDTOToEntity(productoDTO));

        return listaProductosEntity;

    }

    public ProductoDTO productoEntityToDTO(ProductoEntity productoEntity) {
        ProductoDTO productoDTO = null;
        productoDTO = new ProductoDTO(
                productoEntity.getId(),
                productoEntity.getCantidad(),
                productoEntity.getPrecio(),
                productoEntity.getImagen(),
                productoEntity.getDescripcion(),
                productoEntity.getNombre()
        );

        return productoDTO;
    }

    public ProductoEntity productoDTOToEntity(ProductoDTO productoDTO) {
        ProductoEntity productoEntity = null;
        productoEntity = new ProductoEntity(
                productoDTO.getCantidad(),
                productoDTO.getPrecio(),
                productoDTO.getImagen(),
                productoDTO.getDescripcion(),
                productoDTO.getNombre()
        );

        return productoEntity;
    }

}
