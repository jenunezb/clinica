package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
public class Cuenta {

    @Id
    private int codigo;

    @Email
    @Column
    private String email;

    @Column
    private String password;

}
