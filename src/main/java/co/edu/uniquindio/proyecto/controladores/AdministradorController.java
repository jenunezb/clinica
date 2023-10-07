package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MedicoDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/administrador")
@AllArgsConstructor
public class AdministradorController {

    private final AdministradorServicio administradorServicio;

    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO> crearMedico(@Valid @RequestBody MedicoDTO medico) {
        try {
            administradorServicio.crearMedico(medico);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,
                    false, "Médico creado correctamente"));
        } catch (Exception e) {
            // Maneja la excepción aquí y crea una respuesta adecuada
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(HttpStatus.BAD_REQUEST,
                    true, e.getMessage()));
        }
    }
}
