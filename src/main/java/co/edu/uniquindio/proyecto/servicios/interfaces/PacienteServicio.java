package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.modelo.entidades.Medico;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

import java.time.LocalTime;
import java.util.List;

public interface PacienteServicio {

    int registrarse(RegistroPacienteDTO registroPacienteDTO) throws Exception; // Método para que un paciente se registre en el sistema.

    int editarPerfil(int codigoPaciente); // Método para que un paciente edite su perfil.

    int editarPerfil(DetallePacienteDTO pacienteDTO) throws Exception;

    void eliminarCuenta(int codigoPaciente) throws Exception; // Método para que un paciente elimine su cuenta.

    DetallePacienteDTO verDetallePaciente(int codigo) throws Exception; //Ver los detalles del paciente. para hacer modificaciones

    void enviarLinkRecuperacion( String email) throws Exception; // Método para enviar un enlace de recuperación de contraseña al paciente.

    void cambiarPassword(NuevaPasswordDTO nuevaPasswordDTO); // Método para que un paciente cambie su contraseña.

    int agendarCita(RegistroCitaDTO registroCitaDTO) throws Exception; // Método para que un paciente agende una cita médica.

    void crearPQRS(RegistroPQRSDTO registroPQRSDTO); // Método para que un paciente cree una PQRS (Peticiones, Quejas, Reclamos y Sugerencias).

    void responderPQRS(); // Método para que un paciente responda a una PQRS.

    List<ItemPacienteDTO> listarTodos(); // Método para listar las citas médicas agendadas por el paciente.

    void filtrarCitasPorFecha(); // Método para filtrar las citas médicas por fecha.

    void filtrarCitasPorMedico(); // Método para filtrar las citas médicas por médico.

    DetalleAtencionMedicaDTO verDetalleCita(); // Método para ver el detalle de una cita médica.

    List<MedicoPostDTO> mostrarMedicosDisponibles(LocalTime hora, Especialidad especialidad)throws Exception;
}
