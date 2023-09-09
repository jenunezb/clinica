package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Paciente extends Usuario implements Serializable {

    private LocalDate fechaNacimiento;

    private Eps eps;

    private TipoSangre tipoSangre;

    private Alergia alergia;
}
