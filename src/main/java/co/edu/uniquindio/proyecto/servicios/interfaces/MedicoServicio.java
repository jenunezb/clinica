package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ItemCitaDTO;
import co.edu.uniquindio.proyecto.modelo.entidades.Cita;

import java.util.List;

public interface MedicoServicio {

    List<ItemCitaDTO> listarCitasPendientes(int codigoMedico);

    void atenderCita();

    void listarCitasPaciente(); //Historial médico

    void agendarDiaLibre();

    void listarCitasRealizadasMedico();
}