package com.cice.gestioncarrito.web.rest;

import com.cice.gestioncarrito.web.dto.CarritoDTO;
import com.cice.gestioncarrito.web.dto.ProductoDTO;

//este es el controlador
public class CarritoResource {

    //Nada más empezar, tengo que crear un carrito de la compra.
    //Habría que, en la lógica de negocio, hacer la comprobación de que, efectivamente, el usuario está logeado.
    //Tengo que hacer la comprobación de si ya tiene un carrito asociado y devolvérselo
    public CarritoDTO crearCarrito(Long idUsuario) {
        CarritoDTO carritoDTO = null;

        return carritoDTO;
    }

    //Tengo que comprobar si los hay suficientes productos de la cantidad que me están pidiendo
    //También hay que comprobar si existe ese producto que me están pasando
    public CarritoDTO anadirProducto(ProductoDTO productoDTO, int cantidad, Long idCarrito){
        CarritoDTO carritoDTO = null;

        return carritoDTO;
    }

    //Tengo que borrar de la lista tantos de una cosa como me están pidiendo.
    //Hay que comprobar si, efectivamente, hay algo de ese producto en el carrito y, luego, restar.
    //Tengo que comprobar si existe ese producto que me están pasando
    public CarritoDTO eliminarProducto(Long idProducto, Integer cantidad, Long idCarrito){
        CarritoDTO carritoDTO = null;

        return carritoDTO;
    }

    //Tengo que enseñar todos los productos que hay en el carrito y la suma de sus valores
    public CarritoDTO mostrarCarrito(Long idCarrito){
        CarritoDTO carritoDTO = null;

        return carritoDTO;
    }

    //Le mando mi carrito a Pedidos
    public CarritoDTO cerrarCompra() {
        CarritoDTO carritoDTO = null;

        return carritoDTO;
    }

    //Quito todos los productos que hay en el carrito
    public CarritoDTO vaciarCarrito(Long idCarito) {
        CarritoDTO carritoDTO = null;

        return carritoDTO;
    }

/*
    public CarritoDTO cargarCarrito(Long idUsuario) {
        CarritoDTO carritoDTO = null;

        return carritoDTO;
    }
*/
}
