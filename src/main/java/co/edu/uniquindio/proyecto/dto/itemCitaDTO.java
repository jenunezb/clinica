package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;

public record itemCitaDTO(
        int codigoCita,
        EstadoCita estadoCita
        // va la lista de lo que quiero mostrar ahí
) {
}
