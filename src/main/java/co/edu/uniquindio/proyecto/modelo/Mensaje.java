package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Mensaje implements Serializable {

    @Id
    private int codigo;

    private String contenido;

    @ManyToOne
    private Pqrs pqrs;

    @ManyToOne
    private Cuenta cuenta;

    @OneToOne
    private Mensaje mensaje;
}
