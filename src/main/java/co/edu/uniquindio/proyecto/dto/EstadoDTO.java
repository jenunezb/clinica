package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoPQRS;

public record EstadoDTO(
        int codigoPQRS,
        EstadoPQRS estadoPQRS
) {
}
