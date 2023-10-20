package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.HistorialConsultas;
import co.edu.uniquindio.proyecto.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.RespuestaDTO;
import co.edu.uniquindio.proyecto.dto.paciente.ItemPacienteDTO;
import co.edu.uniquindio.proyecto.modelo.entidades.Pqrs;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPQRS;

import java.util.List;

public interface AdministradorServicio {

    int crearMedico(MedicoDTO medicoDTO)throws Exception;

    int actualizarMedico(MedicoDTO medicoDTO)throws Exception;

    void eliminarMedico(int codigo)throws Exception;

    List<ItemMedicoDTO> listarMedicos()throws Exception;

    DetalleMedicoDTO obtenerMedico(int codigo)throws Exception;

    List<ItemPQRSDTO> listarPQRS()throws Exception;

    DetallePQRSDTO verDetallePQRS (int codigo) throws Exception;

    void responderPQRS(RespuestaDTO respuestaDTO)throws Exception;

    List<ItemCitaAdminDTO> listarCitas()throws Exception;

    void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS)throws Exception;

    public List<HistorialConsultas> verHistorialDeConsultas();

    public List<HistorialConsultas> verHistorialDeConsultasMedico(int codigoMedico);

    List<ItemPacienteDTO> listarTodos(); // Método para listar las citas médicas agendadas por el paciente.

}
