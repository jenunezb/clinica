package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.entidades.Horario;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

import java.util.List;

public record ItemMedicoDTO(
        int cedula,
        String nombre,
        String urlFoto,
        Especialidad especialidad) {
}

