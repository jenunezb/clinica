package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ItemCitaDTO;
import co.edu.uniquindio.proyecto.modelo.entidades.Cita;
import co.edu.uniquindio.proyecto.repositorios.CitaRepo;
import co.edu.uniquindio.proyecto.repositorios.PacienteRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.MedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {

    private final CitaRepo citaRepo;

    @Override
    public List<ItemCitaDTO> listarCitasPendientes(int codigoMedico) {
        List<Cita> citas = citaRepo.listarCitasPorEstadoAsignado(codigoMedico);
        List<ItemCitaDTO> respuesta = new ArrayList<>();

        for (Cita c: citas) {
            respuesta.add(new ItemCitaDTO(
                    c.getCodigo(),
                    c.getEstadoCita(),
                    c.getPaciente().getNombre(),
                    c.getFechaCita()
            ));
        }
        return respuesta;
    }

    @Override
    public void atenderCita() {

    }

    @Override
    public void listarCitasPaciente() {

    }

    @Override
    public void agendarDiaLibre() {

    }

    @Override
    public void listarCitasRealizadasMedico() {

    }
}
