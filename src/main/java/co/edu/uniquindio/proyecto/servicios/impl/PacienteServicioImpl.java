package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.proyecto.dto.NuevaPasswordDTO;
import co.edu.uniquindio.proyecto.dto.itemCitaDTO;
import co.edu.uniquindio.proyecto.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroCitaDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPQRSDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServicioImpl implements PacienteServicio {
    @Override
    public int registrarse(RegistroPacienteDTO registroPacienteDTO) {
        return 0;
    }

    @Override
    public int editarPerfil(int codigoPaciente) {
        return 0;
    }

    @Override
    public void eliminarCuenta(int codigoPaciente) throws Exception {

    }

    @Override
    public DetallePacienteDTO detallePaciente() throws Exception {
        return null;
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

    }

    @Override
    public void cambiarPassword(NuevaPasswordDTO nuevaPasswordDTO) {

    }

    @Override
    public int agendarCita(RegistroCitaDTO registroCitaDTO) throws Exception {
        return 0;
    }

    @Override
    public void crearPQRS(RegistroPQRSDTO registroPQRSDTO) {

    }


    @Override
    public void responderPQRS() {

    }

    @Override
    public List<itemCitaDTO> listarCitasPaciente() {
        return null;
    }

    @Override
    public void filtrarCitasPorFecha() {

    }

    @Override
    public void filtrarCitasPorMedico() {

    }

    @Override
    public DetalleAtencionMedicaDTO verDetalleCita() {
        return null;
    }
}
