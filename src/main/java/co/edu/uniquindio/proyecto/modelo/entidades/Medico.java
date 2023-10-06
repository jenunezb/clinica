package co.edu.uniquindio.proyecto.modelo.entidades;


import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Medico extends Usuario implements Serializable {

    @Column(nullable = false)
    private Especialidad especialidad;

    @OneToMany(mappedBy = "medico")
    private List<Horario> horarios;

    private String urlFoto;

    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;

}