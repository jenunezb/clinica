package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.ItemCitaAdminDTO;
import co.edu.uniquindio.proyecto.dto.ItemCitaDTO;
import co.edu.uniquindio.proyecto.dto.ItemPQRSDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.excepciones.Excepciones;
import co.edu.uniquindio.proyecto.modelo.entidades.Paciente;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteServicio pacienteServicio;

    @PutMapping("/editar-perfil")
    public ResponseEntity<MensajeDTO<String>> editarPerfil(@Valid @RequestBody DetallePacienteDTO detallePacienteDTO) throws Exception {
         pacienteServicio.editarPerfil(detallePacienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente editado correctamente") );

    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable int codigo) throws
            Exception{
        pacienteServicio.eliminarCuenta(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Su cuenta se ha eliminado correctamente, por lo tanto su sesión ya no está activa")
        );
    }

    @GetMapping
    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacion(@RequestParam String correo) throws Exception{
            pacienteServicio.enviarLinkRecuperacion(correo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha enviado un link a su correo electrónico para recuperar su contraseña") );

    }

    @PostMapping("/listar-medicos")
    public ResponseEntity<MensajeDTO> medicosDisponibles(@Valid @RequestBody MedicosDisponiblesDTO medicosDisponiblesDTO) throws Excepciones {
        try {
            List<MedicosDisponiblesGetDTO> medicosDisponiblesGetDTOS = pacienteServicio.mostrarMedicosDisponibles(medicosDisponiblesDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                    false, medicosDisponiblesGetDTOS));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @PostMapping("/cita")
    public ResponseEntity<MensajeDTO> agendarCita(@Valid @RequestBody RegistroCitaDTO registroCitaDTO) throws Exception{

        try {
            int paciente = pacienteServicio.agendarCita(registroCitaDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                    false, "Su cita se ha agendado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @PostMapping("/crear-pqrs/")
    public ResponseEntity<MensajeDTO>  crearPQRS(@Valid @RequestBody RegistroPQRSDTO registroPQRSDTO) throws Exception{
            pacienteServicio.crearPQRS(registroPQRSDTO);
        System.out.println("llega hasta aqui 2");
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                    false, "Su cita se ha agendado exitosamente"));
    }

    @Operation(summary = "Detalle paciente", description = "Permite acceder a todos los atributos del paciente dado su código")
    @GetMapping("/detalle/{codigo}")
    public DetallePacienteDTO verDetallePaciente(@PathVariable int codigo) throws Exception{
        return pacienteServicio.verDetallePaciente(codigo);
    }

    @GetMapping("/listar-pqrs/{codigo}")
    public ResponseEntity<MensajeDTO> listarPQRSPaciente(@PathVariable int codigo) throws Exception{
        List<ItemPQRSDTO> itemPQRSDTOList = pacienteServicio.listarPQRSPaciente(codigo);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                false, itemPQRSDTOList));
    }

    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO> obtenerPaciente(@PathVariable int codigo) {
        try {
            DetallePacienteDTO detallePacienteDTO = pacienteServicio.obtenerPaciente(codigo);
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                    false, detallePacienteDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @GetMapping("/listar-citas/{codigo}")
    public ResponseEntity<MensajeDTO> listarCitas(@PathVariable int codigo){
        try {
            List<ItemCitaAdminDTO> lista = pacienteServicio.listarCitas(codigo);
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                    false, lista));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }

    @GetMapping("/historial/{codigo}")
    public ResponseEntity<MensajeDTO> historialMedico(@PathVariable int codigo){
        try {
            List<DetalleCita> lista = pacienteServicio.verHistorialMedico(codigo);
            return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                    false, lista));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO(
                    true, e.getMessage()));
        }
    }
}