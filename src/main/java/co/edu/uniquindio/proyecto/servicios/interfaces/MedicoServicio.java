package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ItemCitaDTO;
import co.edu.uniquindio.proyecto.dto.medico.*;
import co.edu.uniquindio.proyecto.dto.paciente.DetalleCita;
import co.edu.uniquindio.proyecto.modelo.entidades.Cita;

import java.util.List;

public interface MedicoServicio {

    List<ItemCitaDTO> listarCitasPendientesDia(CitasFechaDTO listaCitasFechaDTO) ;

    List<Cita> listarCitasPendientes(int codigoMedico) ;

    DetalleAtencionMedicaDTO atenderCita(int codigoCita)throws Exception;

    List<DetalleCita> listarHistorialMedico(String codigoPaciente); //Historial médico

    void agendarDiaLibre(AgendarDiaLibre agendarDiaLibre) throws Exception;

    List<AtencionMedica> listarCitasRealizadasMedico(int codigoMedico);

    void finalizarCita(FinalizarCitaDTO finalizarCitaDTO) throws Exception;
}