package co.edu.uniquindio.proyecto.dto.paciente;

import java.time.LocalDate;

public record RegistroPQRSDTO(
        int CodigoCita,
        String movito,
        int codigoPaciente
) {
}
