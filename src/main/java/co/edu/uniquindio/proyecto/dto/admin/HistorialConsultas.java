package co.edu.uniquindio.proyecto.dto.admin;

import java.time.LocalDateTime;

public record HistorialConsultas(
        LocalDateTime fecha,
        String codigoMedico,
        String medico,
        String codigoPaciente,
        String Nombrepaciente
) {
}
