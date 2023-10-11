package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

import java.time.LocalDateTime;

public record MedicosDisponiblesDTO(
    LocalDateTime hora,
    Especialidad especialidad
) {
}
