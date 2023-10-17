package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtencionRepo extends JpaRepository<Atencion, Integer> {

    @Query("select a from Atencion a where a.cita.codigo=:codigoCita")
    Optional<Atencion> buscarAtencionPorCodigoCita(@Param("codigoCita")int codigoCita);
}
