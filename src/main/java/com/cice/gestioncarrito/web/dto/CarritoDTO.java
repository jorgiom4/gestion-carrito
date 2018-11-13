package com.cice.gestioncarrito.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDTO {

        private Long idCarrito;
        private Long idUsuario;
        private Double precioTotal;
        private List<ProductoDTO> productos;

}
