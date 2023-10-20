package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepo extends JpaRepository<Mensaje, Integer> {

    @Query("select m from Mensaje m where m.pqrs.codigo=:codigoPqrs")
    List<Mensaje> findByIdPqrs (@Param("codigoPqrs") int codigoPqrs);
}
