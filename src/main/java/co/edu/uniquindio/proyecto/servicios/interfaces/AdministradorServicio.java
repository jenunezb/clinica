package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPQRS;

import java.util.List;

public interface AdministradorServicio {

    int crearMedico(MedicoDTO medicoDTO)throws Exception;

    int actualizarMedico(int codigo, MedicoDTO medicoDTO)throws Exception;

    void eliminarMedico(int codigo)throws Exception;

    List<ItemMedicoDTO> listarMedicos()throws Exception;

    DetalleMedicoDTO obtenerMedico(int codigo)throws Exception;

    List<ItemPQRSDTO> listarPQRS()throws Exception;

    DetallePQRSDTO verDetallePQRS (int codigo) throws Exception;

    String responderPQRS(int codigo)throws Exception;

    PQRSDTOAdmin verDetallePQRS()throws Exception;

    int responderPQRS (RegistroRespuestaDTO registroRespuestaDTO) throws Exception;

    List<ItemCitaAdminDTO> listarCitas()throws Exception;

    void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS)throws Exception;

    boolean estaRepetidaCedula(int id);

    boolean estaRepetidoCorreo(String correo);

}
