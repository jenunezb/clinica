package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

import java.time.LocalTime;

public record MedicoDTO (

    String nombre,
    int cedula,
    Ciudad ciudad,
    Especialidad especialidad,
    String telefono,
    String correo,
    String password,
    LocalTime horaInicioJornada,
    LocalTime horaFinJornada,
    String urlFoto

){
}
