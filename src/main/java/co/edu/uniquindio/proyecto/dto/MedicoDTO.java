package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

import java.time.LocalDate;

public record MedicoDTO (

    String nombre,
    int cedula,
    Ciudad ciudad,
    Especialidad especialidad,
    String telefono,
    String correo,
    String password,
    LocalDate horaInicioJornada,
    LocalDate horaFinJornada,
    String urlFoto

){
}
