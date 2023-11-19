package co.edu.uniquindio.proyecto.dto.medico;

import java.time.LocalDate;

public record CitasFechaDTO(
        int codigoMedico,
        LocalDate fecha
){
}
