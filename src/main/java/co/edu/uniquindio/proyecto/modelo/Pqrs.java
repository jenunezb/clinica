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
public class Pqrs implements Serializable {

    @Id
    private int codigo;

    private LocalDateTime fechaCreacion, tipo, motivo;

    @ManyToOne
    private Cita cita;

    private EstadoPQRS estadoPQRS;

}
