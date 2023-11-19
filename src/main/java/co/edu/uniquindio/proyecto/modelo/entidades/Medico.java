package co.edu.uniquindio.proyecto.modelo.entidades;


import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Medico extends Usuario implements Serializable {

    @Column(nullable = false)
    private Especialidad especialidad;

    @OneToOne(mappedBy = "medico")
    private Horario horario;

    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;

}