package co.edu.uniquindio.proyecto.servicios.interfaces;

public interface MedicoServicio {

    void listarCitasPendientes();

    void atenderCita();

    void listarCitasPaciente(); //Historial médico

    void agendarDiaLibre();

    void listarCitasRealizadasMedico();
}