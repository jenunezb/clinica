package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

public record ItemMedicoDTO(
                            int cedula,
                            String nombre,
                            String urlFoto,
                            Especialidad especialidad) {
}

