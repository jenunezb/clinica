package co.edu.uniquindio.proyecto.modelo.entidades;


import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion, fechaCita;

    @Column(nullable = false)
    private String motivo;

    @ManyToOne
    @Column(nullable = false)
    private Paciente paciente;

    @ManyToOne
    @Column(nullable = false)
    private Medico medico;

    @Column(nullable = false)
    private EstadoCita estadoCita;
}
