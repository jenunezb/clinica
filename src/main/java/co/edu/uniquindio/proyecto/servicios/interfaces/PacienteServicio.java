package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.proyecto.dto.NuevaPasswordDTO;
import co.edu.uniquindio.proyecto.dto.itemCitaDTO;
import co.edu.uniquindio.proyecto.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroCitaDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPQRSDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPacienteDTO;

import java.util.List;

public interface PacienteServicio {

    int registrarse(RegistroPacienteDTO registroPacienteDTO); // Método para que un paciente se registre en el sistema.

    int editarPerfil(int codigoPaciente); // Método para que un paciente edite su perfil.

    void eliminarCuenta(int codigoPaciente) throws Exception; // Método para que un paciente elimine su cuenta.

    DetallePacienteDTO detallePaciente() throws Exception; //Ver los detalles del paciente. para hacer modificaciones

    void enviarLinkRecuperacion( String email) throws Exception; // Método para enviar un enlace de recuperación de contraseña al paciente.

    void cambiarPassword(NuevaPasswordDTO nuevaPasswordDTO); // Método para que un paciente cambie su contraseña.

    int agendarCita(RegistroCitaDTO registroCitaDTO) throws Exception; // Método para que un paciente agende una cita médica.

    void crearPQRS(RegistroPQRSDTO registroPQRSDTO); // Método para que un paciente cree una PQRS (Peticiones, Quejas, Reclamos y Sugerencias).

    void responderPQRS(); // Método para que un paciente responda a una PQRS.

    List<itemCitaDTO> listarCitasPaciente(); // Método para listar las citas médicas agendadas por el paciente.

    void filtrarCitasPorFecha(); // Método para filtrar las citas médicas por fecha.

    void filtrarCitasPorMedico(); // Método para filtrar las citas médicas por médico.

    DetalleAtencionMedicaDTO verDetalleCita(); // Método para ver el detalle de una cita médica.
}
