package co.edu.uniquindio.proyecto.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cita implements Serializable {

    @Id
    private int codigo;

    private LocalDateTime fechaCreacion, fechaCita;

    private String motivo;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    private EstadoCita estadoCita;
}
