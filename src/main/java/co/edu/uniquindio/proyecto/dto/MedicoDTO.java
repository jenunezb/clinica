package co.edu.uniquindio.proyecto.dto;

public record MedicoDTO (

    String nombre,
    String cedula,
    int codigoCiudad,
    int codigoEspecialidad,
    String telefono,
    String correo,
    String password,
    String horaInicioJornada,
    String horaFinJornada

){
}
