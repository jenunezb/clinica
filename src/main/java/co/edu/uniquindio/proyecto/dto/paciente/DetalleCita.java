package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;

import java.time.LocalDateTime;

public record DetalleCita(
        int codigoCita,
        EstadoCita estadoCita,
        LocalDateTime fechaCita,
        String motivo,
        String medico,
        Especialidad especialidadMedico,
        String notas,
        String diagnostico,
        String tratamiento

) {
}
