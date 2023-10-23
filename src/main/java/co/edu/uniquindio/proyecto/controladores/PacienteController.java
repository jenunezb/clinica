package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteServicio pacienteServicio;

    @PostMapping("/registro")
    public int crearPaciente(@Valid @RequestBody RegistroPacienteDTO registroPacienteDTO) throws Exception {
        return pacienteServicio.registrarse(registroPacienteDTO);
    }

    @PutMapping
    public int editarPerfil(@Valid @RequestBody DetallePacienteDTO detallePacienteDTO) throws Exception {
        return pacienteServicio.editarPerfil(detallePacienteDTO);
    }

    @DeleteMapping("/eliminar/{codigo}")
    public void eliminarCuenta(@RequestParam int codigo) throws Exception {
        pacienteServicio.eliminarCuenta(codigo);
    }

    @GetMapping
    public void enviarLinkRecuperacion(@RequestParam String correo) throws Exception{
            pacienteServicio.enviarLinkRecuperacion(correo);
    }

    @GetMapping("/listaMedicosDisponibles")
    public List<MedicosDisponiblesGetDTO> medicosDisponibles(@Valid @RequestBody MedicosDisponiblesDTO medicosDisponiblesDTO) throws Exception {
          return  pacienteServicio.mostrarMedicosDisponibles(medicosDisponiblesDTO);
    }

    @PostMapping("/cita")
    public int agendarCita(@Valid @RequestBody RegistroCitaDTO registroCitaDTO) throws Exception{
            return  pacienteServicio.agendarCita(registroCitaDTO);
    }

    @PostMapping("/pqrs")
    public void crearPQRS(@Valid @RequestBody RegistroPQRSDTO registroPQRSDTO) throws Exception{
            pacienteServicio.crearPQRS(registroPQRSDTO);
    }


}
