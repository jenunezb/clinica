package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPQRS;

import java.time.LocalDateTime;
import java.util.List;

public record DetallePQRSDTO(
        int codigo,
        EstadoPQRS estado,
        String motivo,
        String nombrePaciente,
        String medico,
        Especialidad especialidad,
        LocalDateTime fecha,
        List<RespuestaDTO> mensajes
) {
}
