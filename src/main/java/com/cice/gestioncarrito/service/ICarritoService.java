package com.cice.gestioncarrito.service;

import com.cice.gestioncarrito.web.dto.CarritoDTO;
import com.cice.gestioncarrito.web.dto.ProductoDTO;

import java.util.Optional;

public interface ICarritoService {

    /**
     * Esto lo que hace es comprobar si el usuario tiene un carrito asociado. Si sí lo tiene, lo carga y lo devuelve.
     * Si no lo tiene, crea uno vacío.
     *
     * Nada más empezar, tengo que crear un carrito de la compra.
     * Habría que, en la lógica de negocio, hacer la comprobación de que, efectivamente, el usuario está logeado.
     * Tengo que hacer la comprobación de si ya tiene un carrito asociado y devolvérselo
     *
     *
     */

    CarritoDTO crearCarrito(Long idUsuario);

    /**
     * Tengo que comprobar si los hay suficientes productos de la cantidad que me están pidiendo
     * También hay que comprobar si existe ese producto que me están pasando
     *
     * @param
     * @param cantidad
     * @return
     */

    CarritoDTO anadirProducto(ProductoDTO productoDTO, Integer cantidad, Long idCarrito);

    /**
     * Tengo que borrar de la lista tantos de una cosa como me están pidiendo.
     * Hay que comprobar si, efectivamente, hay algo de ese producto en el carrito y, luego, restar.
     * Tengo que comprobar si existe ese producto que me están pasando
     *
     *
     */

    CarritoDTO eliminarProducto(Long idProducto, Integer cantidad, Long idCarrito);

    /**
    * Tengo que enseñar todos los productos que hay en el carrito y la suma de sus valores
     * Siempre se devuelve un carrito, aunque este puede estar vacío
     *
     */

    CarritoDTO getCarrito(Long idCarrito);

    /**
     *  Le mando mi carrito a Pedidos
     *
     *
     */

    CarritoDTO cerrarCompra();


    /**
     * Convierto el carrito en un carrito vacío
     *
     *
     * @return
     */

    CarritoDTO vaciarCarrito(Long idCarrito);

}
