package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.HorarioDTO;
import co.edu.uniquindio.proyecto.dto.MedicoPostDTO;
import co.edu.uniquindio.proyecto.modelo.entidades.Horario;
import co.edu.uniquindio.proyecto.modelo.entidades.Medico;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepo extends JpaRepository<Horario, Integer> {


}
