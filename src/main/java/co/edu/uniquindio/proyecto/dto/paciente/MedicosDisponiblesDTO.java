package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MedicosDisponiblesDTO(
        @NotNull
        LocalDate fecha,
        @NotNull
        Especialidad especialidad,
        String cedula
) {
}
