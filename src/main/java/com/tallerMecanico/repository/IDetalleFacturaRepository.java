package com.tallerMecanico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tallerMecanico.entity.DetalleFactura;

@Repository
public interface IDetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {

}
