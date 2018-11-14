package com.cice.gestioncarrito.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("gestion-pedidos") //Esta es la llamada al servicio de Eureka
public interface IPedidosFeign {

    @RequestMapping(method = RequestMethod.GET, path = "/pedidos/{id}") //Aquí se pone la ruta
    String getPedidoByID(@PathVariable(name = "id") Long id);

}