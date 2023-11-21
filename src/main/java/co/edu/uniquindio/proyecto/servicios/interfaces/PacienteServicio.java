package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.excepciones.Excepciones;

import java.time.LocalDate;
import java.util.List;

public interface PacienteServicio {

    int registrarse(RegistroPacienteDTO registroPacienteDTO) throws Exception; // Método para que un paciente se registre en el sistema.

    int editarPerfil(DetallePacienteDTO pacienteDTO) throws Exception;

    void eliminarCuenta(int codigoPaciente) throws Exception; // Método para que un paciente elimine su cuenta.

    DetallePacienteDTO verDetallePaciente(int codigo) throws Exception; //Ver los detalles del paciente. para hacer modificaciones

    void enviarLinkRecuperacion( String email) throws Exception; // Método para enviar un enlace de recuperación de contraseña al paciente.

    void cambiarPassword(NuevaPasswordDTO nuevaPasswordDTO) throws Exception; // Método para que un paciente cambie su contraseña.

    int agendarCita(RegistroCitaDTO registroCitaDTO) throws Exception; // Método para que un paciente agende una cita médica.

    void crearPQRS(RegistroPQRSDTO registroPQRSDTO) throws Exception; // Método para que un paciente cree una PQRS (Peticiones, Quejas, Reclamos y Sugerencias).

    List<ItemPQRSDTO> listarPQRSPaciente(int codigoPaciente);//

    DetallePQRSDTO responderPQRS(ResponderPqrsPaciente responderPqrsPaciente) throws Exception; // Método para que un paciente responda a una PQRS.

    List<DetalleCita> filtrarCitasPorFecha(String codigoPaciente, LocalDate fecha); // Método para filtrar las citas médicas por fecha.

    List<DetalleCita> filtrarCitasPorMedico(int codigoPaciente, int codigoMedico); // Método para filtrar las citas médicas por médico.

    List<DetalleCita> verHistorialMedico(int codigoPaciente); // Método para ver el detalle de una cita médica.

    List<MedicosDisponiblesGetDTO> mostrarMedicosDisponibles(MedicosDisponiblesDTO medicosDisponiblesDTO)throws Excepciones;

    DetallePacienteDTO obtenerPaciente (int codigo) throws Exception;

    List<ItemCitaAdminDTO> listarCitas(int codigo)throws Exception;

    DetalleCita detalleHistorialMedico(int codigo)throws Exception;
}
