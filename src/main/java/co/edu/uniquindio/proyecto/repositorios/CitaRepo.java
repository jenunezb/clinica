package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepo extends JpaRepository<Cita, Integer> {

    @Query("SELECT c FROM Cita c WHERE c.paciente.cedula = :codigoPaciente")
    List<Cita> findCitasByPacienteId(@Param("codigoPaciente") int codigoPaciente);

    @Query("SELECT c FROM Cita c WHERE c.estadoCita = 0 and c.medico.cedula=:codigoMedico and DATE(c.fechaCita) = :fecha")
    List<Cita> listarCitasPorEstadoAsignado(@Param("codigoMedico") int codigoMedico, @Param("fecha") LocalDate fecha);

    @Query("select c from  Cita c where c.estadoCita = 2")
    List<Cita> listarCitasFinalizadas();

    @Query("select c from  Cita c where c.estadoCita = 2 and c.medico.cedula=:codigoMedico")
    List<Cita> listarCitasFinalizadasPorMedico(@Param("codigoMedico") int codigoMedico);

    @Query("SELECT c FROM Cita c WHERE c.estadoCita = 0 and c.medico.cedula=:codigoMedico and c.fechaCita > CURRENT_DATE ")
    List<Cita> listarCitasAFuturo (@Param("codigoMedico") int codigoMedico);

    @Query("select c from Cita c where c.estadoCita = 2 and c.medico.cedula=:codigoMedico")
    List<Cita> historialDeAtenciones (@Param("codigoMedico") int codigoMedico);

    @Query("select c from Cita c where c.paciente.cedula=:codigoPaciente and DATE(c.fechaCita) = :fecha")
    List<Cita> listaCitasPorFecha (@Param("codigoPaciente") int codigoPaciente, @Param("fecha") LocalDate fecha);

    @Query("select c from Cita c where c.medico.cedula=:codigoMedico and c.paciente.cedula=:codigoPaciente")
    List<Cita> listaFechasPorMedico (@Param("codigoPaciente") int codigoPaciente, @Param("codigoMedico") int codigoMedico);
}
