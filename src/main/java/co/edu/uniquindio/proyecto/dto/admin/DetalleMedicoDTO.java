package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.dto.HorarioDTO;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

import java.util.List;

public record DetalleMedicoDTO(
        String nombre,
        int cedula,
        Ciudad ciudad,
        Especialidad especialidad,
        String telefono,
        String correo,
        String urlFoto,
        List<HorarioDTO> horarios
) {
}
