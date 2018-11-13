package com.cice.gestioncarrito.service.impl;

import com.cice.gestioncarrito.data.entity.CarritoEntity;
import com.cice.gestioncarrito.data.entity.ProductoEntity;
import com.cice.gestioncarrito.data.repository.CarritoRepository;
import com.cice.gestioncarrito.service.ICarritoService;
import com.cice.gestioncarrito.service.converter.CarritoConverter;
import com.cice.gestioncarrito.service.converter.ProductoConverter;
import com.cice.gestioncarrito.web.dto.CarritoDTO;
import com.cice.gestioncarrito.web.dto.ProductoDTO;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoService implements ICarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoConverter carritoConverter;

    @Autowired
    private ProductoConverter productoConverter;

    @Override
    public CarritoDTO crearCarrito(Long idUsuario) {
        CarritoDTO carritoDTO = null;
        Optional<CarritoEntity> carritoEntityOptional = carritoRepository.findByIdUsuario(idUsuario);

        if(carritoEntityOptional.isPresent()) {
            CarritoEntity carritoEntityDesempaquetado = carritoEntityOptional.get();
            carritoDTO = carritoConverter.CarritoEntityToDTO(carritoEntityDesempaquetado);
        } else {
            carritoDTO = carritoConverter.CarritoEntityToDTO(crearNuevoCarrito(idUsuario));
        }

        return carritoDTO;
    }

    @Override
    public CarritoDTO anadirProducto(ProductoDTO productoDTO, Integer cantidad, Long idCarrito) {
        CarritoDTO carritoDTOSalida = null;
        Optional<CarritoEntity> carritoEntityOptional = carritoRepository.findById(idCarrito);
        if(carritoEntityOptional.isPresent()) {
            CarritoEntity carritoEntityDesempaquetado = carritoEntityOptional.get();

            List<ProductoEntity> listaProductosEntity = carritoEntityDesempaquetado.getProductos();

            ProductoEntity productoEntity = hayProducto(listaProductosEntity, productoDTO.getIdProducto());

            if (productoEntity != null && productoEntity.getCantidad() > cantidad)
                productoEntity.setCantidad(productoEntity.getCantidad() + cantidad);
            else if (productoEntity == null) {
                ProductoEntity nuevoProductoEntity = productoConverter.productoDTOToEntity(productoDTO);
                carritoEntityDesempaquetado.getProductos().add(nuevoProductoEntity);

                carritoDTOSalida = carritoConverter.CarritoEntityToDTO(carritoEntityDesempaquetado);

            }

        }
        return carritoDTOSalida;

    }

    private ProductoEntity hayProducto(List<ProductoEntity> listaProductosEntity, Long idProducto) {
        for(ProductoEntity productoEntity : listaProductosEntity) {
            if(idProducto.equals(productoEntity.getId()))
                return productoEntity;
        }
        return null;
    }

    @Override
    public CarritoDTO eliminarProducto(Long idProducto, Integer cantidad, Long idCarrito) {
        CarritoDTO carritoDTOSalida = null;

        Optional<CarritoEntity> carritoEntityOptional = carritoRepository.findById(idCarrito);
        if(carritoEntityOptional.isPresent()) {

            CarritoEntity carritoEntityDesempaquetado = carritoEntityOptional.get();

            List<ProductoEntity> listaProductosEntity = carritoEntityDesempaquetado.getProductos();

            ProductoEntity productoEntity = hayProducto(listaProductosEntity,idProducto);

            listaProductosEntity.remove(productoEntity);

            restarCantidades(productoEntity,cantidad);

            if(productoEntity.getCantidad() > 0)
                listaProductosEntity.add(productoEntity);

            carritoEntityDesempaquetado.setProductos(listaProductosEntity);

            carritoDTOSalida = carritoConverter.CarritoEntityToDTO(carritoEntityDesempaquetado);

        }

        return carritoDTOSalida;
    }

    private void restarCantidades (ProductoEntity productoEntity, Integer cantidad) {
        if(productoEntity.getCantidad()-cantidad > 0)
            productoEntity.setCantidad(productoEntity.getCantidad()-cantidad);
        else
            productoEntity.setCantidad(0);
    }

    @Override
    public CarritoDTO mostrarCarrito(Long idCarrito) {
        CarritoDTO carritoDTOSalida = null;

        Optional<CarritoEntity> carritoEntityOptional = carritoRepository.findById(idCarrito);

        if(carritoEntityOptional.isPresent()) {

            CarritoEntity carritoEntityDesempaquetado = carritoEntityOptional.get();

            carritoDTOSalida = carritoConverter.CarritoEntityToDTO(carritoEntityDesempaquetado);

        }

        return carritoDTOSalida;

    }

    @Override
    public CarritoDTO cerrarCompra() {
        return null;
    }

    @Override
    public CarritoDTO vaciarCarrito(Long idCarrito) {
        CarritoDTO carritoDTOSalida = null;

        Optional<CarritoEntity> carritoEntityOptional = carritoRepository.findById(idCarrito);

        if (carritoEntityOptional.isPresent()) {

            CarritoEntity carritoEntity = carritoEntityOptional.get();
            carritoEntity.getProductos().clear();

            carritoDTOSalida = carritoConverter.CarritoEntityToDTO(carritoEntity);

        }
        return carritoDTOSalida;
    }


    private CarritoEntity crearNuevoCarrito(Long idUsuario) {
        CarritoEntity carritoEntity = null;

        carritoRepository.save(new CarritoEntity(idUsuario));

        return carritoEntity;
    }



}
