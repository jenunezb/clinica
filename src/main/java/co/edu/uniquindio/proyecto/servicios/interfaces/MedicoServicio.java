package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ItemCitaDTO;
import co.edu.uniquindio.proyecto.dto.medico.AgendarDiaLibre;
import co.edu.uniquindio.proyecto.dto.medico.CitasFechaDTO;
import co.edu.uniquindio.proyecto.dto.medico.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.proyecto.dto.medico.FinalizarCitaDTO;
import co.edu.uniquindio.proyecto.modelo.entidades.Cita;

import java.util.List;

public interface MedicoServicio {

    List<ItemCitaDTO> listarCitasPendientesDia(CitasFechaDTO listaCitasFechaDTO) ;

    List<Cita> listarCitasPendientes(int codigoMedico) ;

    DetalleAtencionMedicaDTO atenderCita(int codigoCita)throws Exception;

    void listarCitasPaciente(); //Historial médico

    void agendarDiaLibre(AgendarDiaLibre agendarDiaLibre) throws Exception;

    void listarCitasRealizadasMedico();

    void finalizarCita(FinalizarCitaDTO finalizarCitaDTO) throws Exception;
}