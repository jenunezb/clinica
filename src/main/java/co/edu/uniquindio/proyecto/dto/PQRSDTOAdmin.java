package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDateTime;

public record PQRSDTOAdmin(
        int codigo,
        int estadoPQRS,
        String nombrePaciente,
        LocalDateTime fecha
){

}
