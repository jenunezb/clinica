package co.edu.uniquindio.proyecto.dto;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPQRS;
import java.time.LocalDateTime;

public record ItemPQRSDTO(int codigo,
                          EstadoPQRS estado,
                          String motivo,
                          LocalDateTime fecha,
                          String nombrePaciente){
}
