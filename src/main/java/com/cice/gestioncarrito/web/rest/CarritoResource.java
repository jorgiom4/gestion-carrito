package com.cice.gestioncarrito.web.rest;

import com.cice.gestioncarrito.service.ICarritoService;
import com.cice.gestioncarrito.web.dto.CarritoDTO;
import com.cice.gestioncarrito.web.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/carrito")
public class CarritoResource {

    @Autowired
    private ICarritoService usuarioService;

    //Nada más empezar, tengo que crear un carrito de la compra.
    //Habría que, en la lógica de negocio, hacer la comprobación de que, efectivamente, el usuario está logeado.
    //Tengo que hacer la comprobación de si ya tiene un carrito asociado y devolvérselo
    @RequestMapping(method = RequestMethod.POST)
    public CarritoDTO crearCarrito(@RequestBody Long idUsuario) {
        CarritoDTO carritoDTO = usuarioService.crearCarrito(idUsuario);

        return carritoDTO;
    }

    //Tengo que comprobar si los hay suficientes productos de la cantidad que me están pidiendo
    //También hay que comprobar si existe ese producto que me están pasando
    @RequestMapping(method = RequestMethod.PUT)
    public CarritoDTO anadirProducto(@RequestBody ProductoDTO productoDTO, @RequestBody Integer cantidad, @RequestBody Long idCarrito){

            CarritoDTO carritoDTO = usuarioService.anadirProducto(productoDTO, cantidad, idCarrito);


        return carritoDTO;
    }

    //Tengo que borrar de la lista tantos de una cosa como me están pidiendo.
    //Hay que comprobar si, efectivamente, hay algo de ese producto en el carrito y, luego, restar.
    //Tengo que comprobar si existe ese producto que me están pasando
    @RequestMapping(method = RequestMethod.DELETE, path = "/{idProducto}")
    public CarritoDTO eliminarProducto(@PathVariable(name = "idProducto") Long idProducto,@RequestBody Integer cantidad, @RequestBody Long idCarrito){
        CarritoDTO carritoDTO = usuarioService.eliminarProducto(idProducto,cantidad,idCarrito);

        return carritoDTO;
    }

    //Tengo que enseñar todos los productos que hay en el carrito y la suma de sus valores
    @RequestMapping(method = RequestMethod.GET)
    public CarritoDTO mostrarCarrito(@RequestBody Long idCarrito){
        CarritoDTO carritoDTO = usuarioService.getCarrito(idCarrito);

        return carritoDTO;
    }

    //Le mando mi carrito a Pedidos
    public CarritoDTO cerrarCompra() {
        CarritoDTO carritoDTO = null;

        return carritoDTO;
    }

    //Quito todos los productos que hay en el carrito
    @RequestMapping(method = RequestMethod.DELETE)
    public CarritoDTO vaciarCarrito(@RequestBody Long idCarrito) {
        CarritoDTO carritoDTO = usuarioService.vaciarCarrito(idCarrito);

        return carritoDTO;
    }

}
