package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;

import java.time.LocalDateTime;

public record ItemCitaDTO(
        int codigoCita,
        EstadoCita estadoCita,
        String paciente,
        LocalDateTime fecha
        // va la lista de lo que quiero mostrar ahí
) {
}
