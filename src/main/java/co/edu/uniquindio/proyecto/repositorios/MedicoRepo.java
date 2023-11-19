package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.DiaLibre;
import co.edu.uniquindio.proyecto.modelo.entidades.Medico;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepo extends JpaRepository<Medico, Integer> {

    Optional<Medico> findByCorreo(String correo);

    Optional<Medico> findByCedula(String cedula);

    boolean existsByCedula(String cedula);

    @Query("SELECT m FROM Medico m WHERE m.especialidad = :especialidad AND m.cedula NOT IN (SELECT d.medico.cedula FROM DiaLibre d WHERE d.dia = :diaCita)")
    List<Medico> findMedicosByEspecialidadAndHorario(@Param("especialidad") Especialidad especialidad, @Param("diaCita") LocalDate diaCita);

}
