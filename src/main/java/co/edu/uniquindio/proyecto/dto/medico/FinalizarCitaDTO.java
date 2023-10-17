package co.edu.uniquindio.proyecto.dto.medico;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;

public record FinalizarCitaDTO(
        int codigoCita,
        String diagnostico,
        String notas_medicas,
        String tratamiento
) {
}
