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
        Optional<CarritoEntity> carritoEntityOptional = carritoRepository.findByIdUsuario(idUsuario); //Busco si el id del usuario que me han pasado tiene ya un carrito

        if(carritoEntityOptional.isPresent()) { //Entra si el usuario ya tenía un carrito
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
        Optional<CarritoEntity> carritoEntityOptional = carritoRepository.findById(idCarrito);//Encuentro el carrito que hay con el id que me están pasando
        if(carritoEntityOptional.isPresent()) { //Si existe ese carrito, opero con él
            CarritoEntity carritoEntityDesempaquetado = carritoEntityOptional.get();

            List<ProductoEntity> listaProductosEntity = carritoEntityDesempaquetado.getProductos();

            ProductoEntity productoEntity = hayProducto(listaProductosEntity, productoDTO.getIdProducto());

            if (productoEntity != null && productoEntity.getCantidad() < cantidad)
                productoEntity.setCantidad(cantidad);
            else if (productoEntity == null) { //Si me devuelve un null, es que no hay de ese producto, así que lo tengo que añadir de cero
                productoDTO.setCantidad(cantidad);
                productoEntity = productoConverter.productoDTOToEntity(productoDTO);
                carritoEntityDesempaquetado.getProductos().add(productoEntity);

                carritoDTOSalida = carritoConverter.CarritoEntityToDTO(carritoEntityDesempaquetado);
            }

        }
        return carritoDTOSalida;

    }

    @Override
    public CarritoDTO eliminarProducto(Long idProducto, Integer cantidad, Long idCarrito) {
        CarritoDTO carritoDTOSalida = null;

        Optional<CarritoEntity> carritoEntityOptional = carritoRepository.findById(idCarrito);
        if(carritoEntityOptional.isPresent()) {

            CarritoEntity carritoEntityDesempaquetado = carritoEntityOptional.get();

            List<ProductoEntity> listaProductosEntity = carritoEntityDesempaquetado.getProductos();

            ProductoEntity productoEntity = hayProducto(listaProductosEntity,idProducto);
            if(productoEntity!= null) {
                listaProductosEntity.remove(productoEntity);

                restarCantidades(productoEntity, cantidad);

                if (productoEntity.getCantidad() > 0)
                    listaProductosEntity.add(productoEntity);
                else
                    listaProductosEntity.remove(productoEntity);

                carritoEntityDesempaquetado.setProductos(listaProductosEntity);

                carritoDTOSalida = carritoConverter.CarritoEntityToDTO(carritoEntityDesempaquetado);
            }
        }

        return carritoDTOSalida;
    }

    @Override
    public CarritoDTO getCarrito(Long idCarrito) {
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

    private void restarCantidades (ProductoEntity productoEntity, Integer cantidad) {
        if(productoEntity.getCantidad()-cantidad > 0)
            productoEntity.setCantidad(productoEntity.getCantidad()-cantidad);
        else
            productoEntity.setCantidad(0);
    }

    private CarritoEntity crearNuevoCarrito(Long idUsuario) {
        CarritoEntity carritoEntity = carritoRepository.save(new CarritoEntity(idUsuario));
        return carritoEntity;
    }

    private ProductoEntity hayProducto(List<ProductoEntity> listaProductosEntity, Long idProducto) {
        for(ProductoEntity productoEntity : listaProductosEntity) {
            if(idProducto.equals(productoEntity.getId()))
                return productoEntity;
        }
        return null;
    }


}
