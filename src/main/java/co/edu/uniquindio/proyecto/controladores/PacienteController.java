package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.HorarioDTO;
import co.edu.uniquindio.proyecto.dto.MedicoDTO;
import co.edu.uniquindio.proyecto.dto.MedicoPostDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("api/paciente")
@AllArgsConstructor
public class PacienteController {

    private final PacienteServicio pacienteServicio;

    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO> crearPaciente(@Valid @RequestBody RegistroPacienteDTO registroPacienteDTO) {
        try {
            pacienteServicio.registrarse(registroPacienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                    false, "Paciente creado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<MensajeDTO> editarPerfil (@Valid @RequestBody DetallePacienteDTO detallePacienteDTO) throws Exception{
        try {
            int codigon = pacienteServicio.editarPerfil(detallePacienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                    false, "Paciente " + codigon + " modificado correctamente"));
        } catch (Exception e) {
            // Maneja la excepción aquí y crea una respuesta adecuada
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }

    }

    @DeleteMapping
    public ResponseEntity<MensajeDTO> eliminarCuenta (@RequestParam int codigo){
        try {
            pacienteServicio.eliminarCuenta(codigo);
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                    false, "Paciente eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<MensajeDTO> enviarLinkRecuperacion (@RequestParam String correo){
        try {
            pacienteServicio.enviarLinkRecuperacion(correo);
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                    false, "Se ha enviado el link de recuperación de cuenta a su correo electronico," +
                    "por favor revise en su bandeja de entrada o en spam"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @PostMapping("/cita")
    public ResponseEntity<MensajeDTO> agendarCita(@Valid @RequestBody RegistroCitaDTO registroCitaDTO){
        try {
            pacienteServicio.agendarCita(registroCitaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                    false, "Cita agendada correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @GetMapping("/listaMedicosDisponibles")
    public ResponseEntity<MensajeDTO> medicosDisponibles(@Valid @RequestBody  MedicosDisponiblesDTO medicosDisponiblesDTO){
        System.out.println("pasa");
        try {
            List<MedicosDisponiblesGetDTO> medicoPostDTOList=pacienteServicio.mostrarMedicosDisponibles(medicosDisponiblesDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                    false, medicoPostDTOList ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }
}
