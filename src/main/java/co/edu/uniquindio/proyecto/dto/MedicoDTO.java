package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import jakarta.validation.constraints.*;
import lombok.NonNull;

import java.time.LocalTime;

public record MedicoDTO(
        @NotBlank
        String nombre,

        @NotNull
        String cedula,

        @NotNull
        Ciudad ciudad,

        @NotNull
        Especialidad especialidad,

        @NotBlank
        String telefono,

        @NotBlank
        @Email
        String correo,

        @NotBlank
        String password,

        @NotNull
        LocalTime horaInicioJornada,

        @NotNull
        LocalTime horaFinJornada,

        @NotBlank
        String urlFoto

) {
}
