package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario extends Admin implements Serializable {

    private String nombre, telefono, foto;

    private Ciudad ciudad;

    private Estado estado;

}
