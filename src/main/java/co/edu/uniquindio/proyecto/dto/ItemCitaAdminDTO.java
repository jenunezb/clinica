package co.edu.uniquindio.proyecto.dto;


import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;

import java.time.LocalDateTime;

public record ItemCitaAdminDTO(

        int codigoCita,
        int cedulaPaciente,
        String nombrePaciente,
        String nombreMedico,
        Especialidad especialidad,
        EstadoCita estadoCita,
        LocalDateTime fecha

) {
}

