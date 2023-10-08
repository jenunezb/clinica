package co.edu.uniquindio.proyecto.modelo.entidades;


import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class Usuario extends Cuenta{

    @Column(nullable = false)
    private String nombre, telefono, foto;

    @Column(nullable = false)
    private Ciudad ciudad;

    @Column(nullable = false)
    private boolean estado;
}
