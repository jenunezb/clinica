package co.edu.uniquindio.proyecto.dto.paciente;

import java.time.LocalDateTime;

public record RegistroCitaDTO(
        LocalDateTime fechaCita,
        String motivo,
        int idPaciente,
        int idMedico
) {
}
