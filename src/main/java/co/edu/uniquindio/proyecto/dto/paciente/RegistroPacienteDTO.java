package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.Alergia;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Eps;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record RegistroPacienteDTO(

        //Datos de la Cuenta

        @NotBlank
        @Length(max = 80, message = "El correo debe tener máximo 80 caracteres")
        @Email(message = "Ingrese una dirección de correo electrónico válida")
        String correo,
        @NotBlank
        String password,

        //Datos del Usuario
        @Min(value = 1, message = "El valor debe ser mayor que cero")
        int cedula,
        @NotBlank
        @Length(max = 200, message = "El nombre debe tener máximo 200 caracteres")
        String nombre,
        @NotBlank
        @Length(max = 20, message = "El teléfono debe tener máximo 20 caracteres")
        String telefono,
        @NotBlank
        String urlFoto,
        @NotNull
        Ciudad ciudad,

        //Datos del Paciente
        @NotNull
        LocalDate fechaNacimiento,
        @NotBlank
        String alergias,
        @NotNull
        Eps eps,
        @NotNull
        TipoSangre tipoSangre
) {

}