package co.edu.uniquindio.proyecto.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Medico extends Usuario implements Serializable {

    private Especialidad especialidad;

}
