package com.cice.gestioncarrito.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("gestion-usuarios") //Esta es la llamada al servicio de Eureka
public interface IUsuarioFeign {

    @RequestMapping(method = RequestMethod.GET, path = "/usuarios/{id}") //Aquí se pone la ruta
    String getUsuarioByID(@PathVariable(name = "id") Long id);

}