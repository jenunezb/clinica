package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Administrador extends Cuenta implements Serializable {

}
