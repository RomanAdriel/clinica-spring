package com.dh.clinica.clinica.repository;

import com.dh.clinica.clinica.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {

    @Query(value = "select * from Domicilios d WHERE d.calle = ?1 and d.numero = ?2 AND d.localidad = ?3 AND d" +
            ".provincia = ?4"
            , nativeQuery = true)
    Domicilio findByCalleAndNumeroAndLocalidadAndProvincia(String calle, int numero, String localidad,
                                                           String provincia);
}
