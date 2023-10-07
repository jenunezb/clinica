package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.modelo.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteServicio extends JpaRepository<Paciente, Integer> {


    void registrarse() throws Exception; // Método para que un paciente se registre en el sistema.

    void eliminarCuenta(); // Método para que un paciente elimine su cuenta.

    void enviarLinkRecuperacion(); // Método para enviar un enlace de recuperación de contraseña al paciente.

    void cambiarPassword(); // Método para que un paciente cambie su contraseña.

    void agendarCita(); // Método para que un paciente agende una cita médica.

    void crearPQRS(); // Método para que un paciente cree una PQRS (Peticiones, Quejas, Reclamos y Sugerencias).

    void listarPQRSPaciente(); // Método para listar las PQRS realizadas por el paciente.

    void responderPQRS(); // Método para que un paciente responda a una PQRS.

    void listarCitasPaciente(); // Método para listar las citas médicas agendadas por el paciente.

    void filtrarCitasPorFecha(); // Método para filtrar las citas médicas por fecha.

    void filtrarCitasPorMedico(); // Método para filtrar las citas médicas por médico.

    void verDetalleCita(); // Método para ver el detalle de una cita médica.
}
