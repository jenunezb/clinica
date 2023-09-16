package co.edu.uniquindio.proyecto.servicios;

public interface MedicoServicio {

    void listarCitasPendientes();

    void atenderCita();

    void listarCitasPaciente(); //Historial médico

    void agendarDiaLibre();

    void listarCitasRealizadasMedico();
}
