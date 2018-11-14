package com.cice.gestioncarrito.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Carritos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="seq",initialValue = 0)
public class CarritoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="seq")
    private Long id;
    @OneToMany
    private List<ProductoEntity> productos;
    private Long idUsuario;

    public CarritoEntity(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
