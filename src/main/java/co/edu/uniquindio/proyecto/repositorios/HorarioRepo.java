package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.HorarioDTO;
import co.edu.uniquindio.proyecto.dto.MedicoPostDTO;
import co.edu.uniquindio.proyecto.modelo.entidades.Horario;
import co.edu.uniquindio.proyecto.modelo.entidades.Medico;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HorarioRepo extends JpaRepository<Horario, Integer> {


    void deleteHorarioByMedico_Codigo(int codigo);

    @Query("select h FROM Horario h WHERE h.medico.codigo = :codigoMedico")
    Optional<Horario> findByMedicoId(@Param("codigoMedico") int codigoMedico);
}
