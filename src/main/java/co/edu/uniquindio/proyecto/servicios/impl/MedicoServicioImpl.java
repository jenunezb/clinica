package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ItemCitaDTO;
import co.edu.uniquindio.proyecto.dto.medico.CitasFechaDTO;
import co.edu.uniquindio.proyecto.dto.medico.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.proyecto.excepciones.Excepciones;
import co.edu.uniquindio.proyecto.modelo.entidades.Atencion;
import co.edu.uniquindio.proyecto.modelo.entidades.Cita;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;
import co.edu.uniquindio.proyecto.repositorios.CitaRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.MedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {

    private final CitaRepo citaRepo;

    @Override
    public List<ItemCitaDTO> listarCitasPendientes(CitasFechaDTO listaCitasFechaDTO) {
        List<Cita> citas = citaRepo.listarCitasPorEstadoAsignado(listaCitasFechaDTO.codigoMedico(), listaCitasFechaDTO.fecha());
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
    public DetalleAtencionMedicaDTO atenderCita(int codigoCita) throws Exception{
            Optional <Cita> citaBuscada = citaRepo.findById(codigoCita);

            if(citaBuscada.isEmpty()){
                throw new Excepciones("Error, El código No existe");
            }

        Atencion atencion = new Atencion();
        DetalleAtencionMedicaDTO detalleAtencionMedicaDTO = new DetalleAtencionMedicaDTO(
                    citaBuscada.get().getPaciente().getCedula(),
                    citaBuscada.get().getPaciente().getNombre(),
                    citaBuscada.get().getPaciente().getTelefono(),
                    citaBuscada.get().getPaciente().getEps(),
                    citaBuscada.get().getPaciente().getTipoSangre(),
                    citaBuscada.get().getPaciente().getCiudad(),

                    citaBuscada.get().getMedico().getEspecialidad(),
                    citaBuscada.get().getMedico().getNombre(),
                    citaBuscada.get().getMotivo(),

                    "",
                "",
                "",
                "");
        return detalleAtencionMedicaDTO;
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
