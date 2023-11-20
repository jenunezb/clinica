package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.EstadoDTO;
import co.edu.uniquindio.proyecto.dto.ItemPQRSDTO;
import co.edu.uniquindio.proyecto.dto.MedicoDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.RespuestaDTO;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPQRS;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("api/admins")
@AllArgsConstructor
public class AdministradorController {

    private final AdministradorServicio administradorServicio;

    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO> crearMedico(@Valid @RequestBody MedicoDTO medico) {
        try {
            administradorServicio.crearMedico(medico);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                    false, "Médico creado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<MensajeDTO> actualizarMedico(@Valid @RequestBody DetalleMedicoDTO medico) {
        try {
            int codigon = administradorServicio.actualizarMedico(medico);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                    false, "Médico " + codigon + " modificado correctamente"));
        } catch (Exception e) {
            // Maneja la excepción aquí y crea una respuesta adecuada
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<MensajeDTO> obtenerMedico(@PathVariable int codigo) {
        try {
            DetalleMedicoDTO detalleMedicoDTO = administradorServicio.obtenerMedico(codigo);
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                    false, detalleMedicoDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<MensajeDTO> eliminarMedico(@RequestParam int codigo) {
        try {
            administradorServicio.eliminarMedico(codigo);
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                    false, "Médico eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @GetMapping("/listaMedicos")
    public ResponseEntity<MensajeDTO> listarMedicos() {
        try {
            List<ItemMedicoDTO> lista = administradorServicio.listarMedicos();
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                    false, lista));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @PostMapping("/registroPQRS")
    public ResponseEntity<MensajeDTO> responderPQRS(@Valid @RequestBody RespuestaDTO respuestaDTO){
        try {
            administradorServicio.responderPQRS(respuestaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                    false, "respuesta enviada al paciente "));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @GetMapping("/listar-pqrs")
    public ResponseEntity<MensajeDTO> listarPQRS(){
        try {
            List<ItemPQRSDTO> lista = administradorServicio.listarPQRS();
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                    false, lista));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @PutMapping("/cambiar-estado-pqrs/")
    public ResponseEntity<MensajeDTO> cambiarEstadoPQRS(@Valid @RequestBody EstadoDTO estadoPQRS){
        try {
            administradorServicio.cambiarEstadoPQRS(estadoPQRS.codigoPQRS(), estadoPQRS.estadoPQRS());

            return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                    false, "Estado Cambiado"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

}
