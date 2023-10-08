package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MedicoDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(HttpStatus.BAD_REQUEST,
                    true, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<MensajeDTO> obtenerMedico (@RequestParam int codigo){
        try {
            DetalleMedicoDTO detalleMedicoDTO = administradorServicio.obtenerMedico(codigo);
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,
                    false, detalleMedicoDTO ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(HttpStatus.BAD_REQUEST,
                    true, e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<MensajeDTO> actualizarMedico(@Valid @RequestBody MedicoDTO medico, int codigo) {
        try {
            int codigon=administradorServicio.actualizarMedico(codigo, medico);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,
                    false, "Médico "+ codigon+" modificado correctamente"));
        } catch (Exception e) {
            // Maneja la excepción aquí y crea una respuesta adecuada
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(HttpStatus.BAD_REQUEST,
                    true, e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<MensajeDTO> eliminarMedico(@RequestParam int codigo) {
        try {
            administradorServicio.eliminarMedico(codigo);
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,
                    false, "Médico eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(HttpStatus.BAD_REQUEST,
                    true, e.getMessage()));
        }
    }

    @GetMapping("/listaMedicos")
    public ResponseEntity<MensajeDTO> listarMedicos (){
        try {
            List<ItemMedicoDTO> lista = administradorServicio.listarMedicos();
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,
                    false, lista ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(HttpStatus.BAD_REQUEST,
                    true, e.getMessage()));
        }
    }

}
