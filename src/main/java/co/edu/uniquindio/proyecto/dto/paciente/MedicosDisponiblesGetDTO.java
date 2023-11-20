package co.edu.uniquindio.proyecto.dto.paciente;

import java.time.LocalTime;

public record MedicosDisponiblesGetDTO (
        String nombreMedico,
        LocalTime horaDisponible,
        int codigoMedico
        ){
}
