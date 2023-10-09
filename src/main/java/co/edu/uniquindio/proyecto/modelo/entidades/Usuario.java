package co.edu.uniquindio.proyecto.modelo.entidades;


import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import jakarta.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode;


@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Usuario extends Cuenta{

    @Column(nullable = false)
    private String nombre, telefono, foto;

    @Column(nullable = false)
    private Ciudad ciudad;

    @Column(nullable = false)
    private boolean estado;
}
