package co.edu.uniquindio.proyecto.dto;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPQRS;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ItemPQRSDTO(int codigo,
                          EstadoPQRS estado,
                          String motivo,
                          LocalDate fecha,
                          String nombrePaciente){
}
