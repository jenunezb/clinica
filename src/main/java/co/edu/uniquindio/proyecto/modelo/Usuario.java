package co.edu.uniquindio.proyecto.modelo;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario extends Cuenta{

    @Column
    private String nombre, telefono, foto;

    @Column
    private Ciudad ciudad;

    @Column
    private Estado estado;
}
