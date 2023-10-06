package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

public record MedicoDTO (

    String nombre,
    int cedula,
    Ciudad codigoCiudad,
    Especialidad codigoEspecialidad,
    String telefono,
    String correo,
    String password,
    String horaInicioJornada,
    String horaFinJornada,
    String urlFoto

){
}
