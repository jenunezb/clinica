package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepo extends JpaRepository<Paciente, Integer> {

    Paciente findByCorreo(String correo);

    boolean existsByCedula(String cedula);

    Optional<Paciente> findById(Integer integer);

    Optional<Paciente> findByCedula(String cedula);

}
