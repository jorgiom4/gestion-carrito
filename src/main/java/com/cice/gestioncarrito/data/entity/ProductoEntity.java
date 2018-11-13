package com.cice.gestioncarrito.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="seq",initialValue = 0)
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="seq")
    private Long id;
    private Integer cantidad;
    private Double precio;
    private String imagen;
    private String descripcion;
    private String nombre;

    public ProductoEntity(Integer cantidad, Double precio, String imagen, String descripcion, String nombre) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }
}
