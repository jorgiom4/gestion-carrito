package com.cice.gestioncarrito.web.rest;

import com.cice.gestioncarrito.feign.*;
import com.cice.gestioncarrito.service.ICarritoService;
import com.cice.gestioncarrito.web.dto.CarritoDTO;
import com.cice.gestioncarrito.web.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/carrito")
public class CarritoResource {

    @Autowired
    private ICarritoService carritoService;

    @Autowired
    private IFacturacionFeign facturacionFeign;

    @Autowired
    private IDevolucionesFeign devolucionesFeign;

    @Autowired
    private IEnviosFeign enviosFeign;

    @Autowired
    private IPedidosFeign pedidosFeign;

    @Autowired
    private IUsuarioFeign usuariosFeign;



    @RequestMapping(method = RequestMethod.GET, path = "/facturacion/{id}", produces = "application/json")
    public String getFacturacionById(){
        Long id = 1L;
        return facturacionFeign.getFacturaByID(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/devoluciones/{id}", produces = "application/json")
    public String getDevolucionById(){
        Long id = 1L;
        return devolucionesFeign.getDevolucionByID(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/envios/{id}", produces = "application/json")
    public String getEnvioById(){
        Long id = 1L;
        return enviosFeign.getEnvioByID(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/pedidos/{id}", produces = "application/json")
    public String getPedidoById(){
        Long id = 1L;
        return pedidosFeign.getPedidoByID(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/usuarios/{id}", produces = "application/json")
    public String getUsuarioById(){
        Long id = 1L;
        return usuariosFeign.getUsuarioByID(id);
    }




    //Nada más empezar, tengo que crear un carrito de la compra.
    //Habría que, en la lógica de negocio, hacer la comprobación de que, efectivamente, el usuario está logeado.
    //Tengo que hacer la comprobación de si ya tiene un carrito asociado y devolvérselo
    @RequestMapping(method = RequestMethod.POST)
    public CarritoDTO crearCarrito(@RequestBody Long idUsuario) {
        CarritoDTO carritoDTO = carritoService.crearCarrito(idUsuario);

        return carritoDTO;
    }

    //Tengo que comprobar si los hay suficientes productos de la cantidad que me están pidiendo
    //También hay que comprobar si existe ese producto que me están pasando
    @RequestMapping(method = RequestMethod.PUT)
    public CarritoDTO anadirProducto(@RequestBody ProductoDTO productoDTO, @RequestBody Integer cantidad, @RequestBody Long idCarrito){

            CarritoDTO carritoDTO = carritoService.anadirProducto(productoDTO, cantidad, idCarrito);


        return carritoDTO;
    }

    //Tengo que borrar de la lista tantos de una cosa como me están pidiendo.
    //Hay que comprobar si, efectivamente, hay algo de ese producto en el carrito y, luego, restar.
    //Tengo que comprobar si existe ese producto que me están pasando
    @RequestMapping(method = RequestMethod.DELETE, path = "/{idProducto}")
    public CarritoDTO eliminarProducto(@PathVariable(name = "idProducto") Long idProducto,@RequestBody Integer cantidad, @RequestBody Long idCarrito){
        CarritoDTO carritoDTO = carritoService.eliminarProducto(idProducto,cantidad,idCarrito);

        return carritoDTO;
    }

    //Tengo que enseñar todos los productos que hay en el carrito y la suma de sus valores
    @RequestMapping(method = RequestMethod.GET)
    public CarritoDTO mostrarCarrito(@RequestBody Long idCarrito){
        CarritoDTO carritoDTO = carritoService.getCarrito(idCarrito);

        return carritoDTO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{idCarrito}")
    public String getCarrito(@PathVariable Long idCarrito){

        return "Aquí tienes tu carrito.";
    }

    //Le mando mi carrito a Pedidos
    private CarritoDTO cerrarCompra() {
        CarritoDTO carritoDTO = null;

        return carritoDTO;
    }

    //Quito todos los productos que hay en el carrito
    @RequestMapping(method = RequestMethod.DELETE)
    public CarritoDTO vaciarCarrito(@RequestBody Long idCarrito) {
        CarritoDTO carritoDTO = carritoService.vaciarCarrito(idCarrito);

        return carritoDTO;
    }



}
