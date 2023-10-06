package co.edu.uniquindio.proyecto.modelo.entidades;


import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    @OneToMany(mappedBy = "cita")
    private List<Pqrs> pqrs;


    @Column(nullable = false)
    private EstadoCita estadoCita;
}
