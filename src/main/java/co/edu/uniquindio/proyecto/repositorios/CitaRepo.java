package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepo extends JpaRepository<Cita, Integer> {

    @Query("SELECT c FROM Cita c WHERE c.paciente.cedula = :clienteId")
    List<Cita> findCitasByClienteId(@Param("clienteId") int clienteId);

    @Query("SELECT c FROM Cita c WHERE c.estadoCita = 0 and c.medico.cedula=:codigoMedico")
    List<Cita> listarCitasPorEstadoAsignado(@Param("codigoMedico") int codigoMedico);
}
