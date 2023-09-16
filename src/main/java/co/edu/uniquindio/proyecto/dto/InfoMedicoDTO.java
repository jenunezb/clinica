package co.edu.uniquindio.proyecto.dto;

public record InfoMedicoDTO(

        int codigo,
        String nombre,
        String cedula,
        int codigoCiudad,
        int codigoEspecialidad,
        String telefono,
        String correo,
        String horaInicioJornada,
        String horaFinJornada

){
}
