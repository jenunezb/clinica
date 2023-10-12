package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Pqrs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PqrsRepo extends JpaRepository<Pqrs, Integer> {

    @Query("SELECT pq FROM Pqrs pq JOIN pq.cita c WHERE c.paciente.cedula = :codigoPaciente")
    List<Pqrs> findByCodigoPaciente(@Param("codigoPaciente") int codigoPaciente);
}
