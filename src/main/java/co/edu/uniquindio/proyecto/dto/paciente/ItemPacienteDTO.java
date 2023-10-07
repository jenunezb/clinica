package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;

public record ItemPacienteDTO(
        int cedula,
        String nombre,
        Ciudad ciudad
) {
}
