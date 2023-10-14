package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ItemCitaDTO;
import co.edu.uniquindio.proyecto.dto.medico.CitasFechaDTO;
import co.edu.uniquindio.proyecto.dto.medico.DetalleAtencionMedicaDTO;

import java.util.List;

public interface MedicoServicio {

    List<ItemCitaDTO> listarCitasPendientes(CitasFechaDTO listaCitasFechaDTO) ;

    DetalleAtencionMedicaDTO atenderCita(int codigoCita)throws Exception;

    void listarCitasPaciente(); //Historial médico

    void agendarDiaLibre();

    void listarCitasRealizadasMedico();
}