package com.cice.gestioncarrito.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("gestion-devoluciones") //Esta es la llamada al servicio de Eureka
public interface IDevolucionesFeign {

    @RequestMapping(method = RequestMethod.GET, path = "/devoluciones/{id}") //Aquí se pone la ruta
    String getDevolucionByID(@PathVariable(name = "id") Long id);

}
