package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

import java.time.LocalTime;

public record MedicosDisponiblesDTO(
    LocalTime hora,
    Especialidad especialidad
) {
}
