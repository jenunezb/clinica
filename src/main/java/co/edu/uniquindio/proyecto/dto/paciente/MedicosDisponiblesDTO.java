package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MedicosDisponiblesDTO(

    LocalDate fecha,
    Especialidad especialidad
) {
}
