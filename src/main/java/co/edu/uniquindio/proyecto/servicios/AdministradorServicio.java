package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.*;

import java.util.List;

public interface AdministradorServicio {

    String crearMedico(MedicoDTO medicoDTO)throws Exception;

    String actualizarMedico(int codigo, MedicoDTO medicoDTO)throws Exception;

    String eliminarMedico(int codigo)throws Exception;

    List<MedicoDTOAdmin> listarMedicos()throws Exception;

    InfoMedicoDTO obtenerMedico(int codigo)throws Exception;

    PQRSDTOAdmin listarPQRS()throws Exception;

    String responderPQRS(int codigo)throws Exception;

    PQRSDTOAdmin verDetallePQRS()throws Exception;

    List<CitaDTOAdmin> listarCitas()throws Exception;

}
