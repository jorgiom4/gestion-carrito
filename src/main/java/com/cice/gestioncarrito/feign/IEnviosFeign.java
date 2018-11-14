package com.cice.gestioncarrito.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("gestion-envios") //Esta es la llamada al servicio de Eureka
public interface IEnviosFeign {

    @RequestMapping(method = RequestMethod.GET, path = "/envios/{id}") //Aquí se pone la ruta
    String getEnvioByID(@PathVariable(name = "id") Long id);

}