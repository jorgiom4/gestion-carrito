package com.cice.gestioncarrito.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("gestion-facturacion") //Esta es la llamada al servicio de Eureka
public interface IFacturacionFeign {

    @RequestMapping(method = RequestMethod.GET, path = "/facturacion/{id}") //Aquí se pone la ruta
    String getFacturaByID(@PathVariable(name = "id") Long id);

}
