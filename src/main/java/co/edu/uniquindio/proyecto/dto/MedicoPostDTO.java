package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

public record MedicoPostDTO(
        String nombreMedico,
        Especialidad especialidad
) {
}
