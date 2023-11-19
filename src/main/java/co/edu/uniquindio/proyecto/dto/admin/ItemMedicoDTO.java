package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

import java.time.LocalTime;

public record ItemMedicoDTO(
        String cedula,
        String nombre,
        String urlFoto,
        Especialidad especialidad,
        LocalTime horaInicio,
        LocalTime horaFin,
        int codigo) {
}

