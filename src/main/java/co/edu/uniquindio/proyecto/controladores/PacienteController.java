package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.HorarioDTO;
import co.edu.uniquindio.proyecto.dto.MedicoDTO;
import co.edu.uniquindio.proyecto.dto.MedicoPostDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.paciente.MedicosDisponiblesDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroCitaDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPacienteDTO;
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
            return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,
                    false, "Paciente creado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(HttpStatus.BAD_REQUEST,
                    true, e.getMessage()));
        }
    }

    @PostMapping("/cita")
    public ResponseEntity<MensajeDTO> agendarCita(@Valid @RequestBody RegistroCitaDTO registroCitaDTO){
        try {
            pacienteServicio.agendarCita(registroCitaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,
                    false, "Cita agendada correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(HttpStatus.BAD_REQUEST,
                    true, e.getMessage()));
        }
    }

    @GetMapping("/listaMedicosDisponibles")
    public ResponseEntity<MensajeDTO> medicosDisponibles(@Valid @RequestBody  MedicosDisponiblesDTO medicosDisponiblesDTO){
        System.out.println("pasa");
        try {
            List<String> medicoPostDTOList=pacienteServicio.mostrarMedicosDisponibles(medicosDisponiblesDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,
                    false, medicoPostDTOList ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(HttpStatus.BAD_REQUEST,
                    true, e.getMessage()));
        }
    }
}
