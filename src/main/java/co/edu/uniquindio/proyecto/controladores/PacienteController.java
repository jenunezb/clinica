package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.excepciones.Excepciones;
import co.edu.uniquindio.proyecto.modelo.entidades.Paciente;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteServicio pacienteServicio;

    @PutMapping
    public ResponseEntity<MensajeDTO<String>> editarPerfil(@Valid @RequestBody DetallePacienteDTO detallePacienteDTO) throws Exception {
         pacienteServicio.editarPerfil(detallePacienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente editado correctamente") );

    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable int codigo) throws
            Exception{
        pacienteServicio.eliminarCuenta(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente eliminado correctamete")
        );
    }

    @GetMapping
    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacion(@RequestParam String correo) throws Exception{
            pacienteServicio.enviarLinkRecuperacion(correo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha enviado un link a su correo electrónico para recuperar su contraseña") );

    }

    @GetMapping("/listaMedicosDisponibles")
    public ResponseEntity<MensajeDTO<List<MedicosDisponiblesGetDTO>>> medicosDisponibles(@Valid @RequestBody MedicosDisponiblesDTO medicosDisponiblesDTO) throws Excepciones {
            List<MedicosDisponiblesGetDTO> medicosDisponiblesGetDTOS = pacienteServicio.mostrarMedicosDisponibles(medicosDisponiblesDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, medicosDisponiblesGetDTOS));
    }

    @PostMapping("/cita")
    public int agendarCita(@Valid @RequestBody RegistroCitaDTO registroCitaDTO) throws Exception{
            return  pacienteServicio.agendarCita(registroCitaDTO);
    }

    @PostMapping("/pqrs")
    public void crearPQRS(@Valid @RequestBody RegistroPQRSDTO registroPQRSDTO) throws Exception{
            pacienteServicio.crearPQRS(registroPQRSDTO);
    }

    @GetMapping("/detalle/{codigo}")
    public DetallePacienteDTO verDetallePaciente(@PathVariable int codigo) throws Exception{
        return pacienteServicio.verDetallePaciente(codigo);
    }
    
}