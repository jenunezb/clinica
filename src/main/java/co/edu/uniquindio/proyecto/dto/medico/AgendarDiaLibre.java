package co.edu.uniquindio.proyecto.dto.medico;

import jakarta.validation.constraints.Future;

import java.time.LocalDate;

public record AgendarDiaLibre(
        int codigoMedico,
        @Future
        LocalDate fecha
) {
}
