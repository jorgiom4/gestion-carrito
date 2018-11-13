package com.cice.gestioncarrito.data.repository;

import com.cice.gestioncarrito.data.entity.CarritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<CarritoEntity, Long> {

    Optional<CarritoEntity> findByIdUsuario(Long idUsuario);

}
